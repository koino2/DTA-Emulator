package dta.devices;

import dta.Pin;

import javax.swing.*;
import java.awt.*;

public class TextWindow extends Device{

    public char[][] screen = new char[64][64];
    public JPanel panel;

    {
        for (int y = 0; y < screen.length; y++) {
            for (int x = 0; x < screen[y].length; x++) {
                screen[y][x] = ' ';
            }
        }
    }

    public Font font = new Font("Fixedsys Regular", Font.PLAIN, 20);

    public TextWindow(){
        JFrame j = new JFrame("Screen");
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                g.setFont(font);
                FontMetrics fm = g.getFontMetrics(font);
                int height = fm.getHeight();
                for (int y = 0; y < screen.length; y++) {
                    int xPos = 0;
                    for (int x = 0; x < screen[y].length; x++) {
                        g.drawString(String.valueOf(screen[y][x]), xPos, height*(y+1));
                        xPos += fm.stringWidth(String.valueOf(screen[y][x]));
                    }
                }
            }
        };
        panel.setSize(640, 640);
        panel.setPreferredSize(new Dimension(640, 640));
        j.add(panel);

        j.pack();

        j.setLocationRelativeTo(null);
        j.setResizable(false);

        j.setVisible(true);
    }

    public void clearRegisters(){
        DSC_D0.setValue(0);
        DSC_D1.setValue(0);
        DSC_D2.setValue(0);
        DSC_D3.setValue(0);
        DSC_D4.setValue(0);
        DSC_D5.setValue(0);
        DSC_D6.setValue(0);
        DSC_D7.setValue(0);
    }

    {
        DSC_P0 = new Pin() {
            @Override
            public void onFire() {
                if (DSC_D0.getValue() == 1) {
                    int x = DSC_D1.getValue();
                    int y = DSC_D2.getValue();
                    int code = DSC_D3.getValue();

                    screen[y][x] = (char) code;
                    panel.repaint();

                    clearRegisters();
                }
            }
        };
    }

    @Override
    public void update() {

    }
}
