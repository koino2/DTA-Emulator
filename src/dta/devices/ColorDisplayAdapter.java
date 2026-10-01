package dta.devices;

import dta.Pin;

import javax.swing.*;
import java.awt.*;

public class ColorDisplayAdapter extends Device{

    public Color[][] screen = new Color[64][64];
    public JPanel panel;

    {
        for (int y = 0; y < screen.length; y++) {
            for (int x = 0; x < screen[y].length; x++) {
                screen[y][x] = new Color(0, 0, 0);
            }
        }
    }

    public ColorDisplayAdapter(){
        JFrame j = new JFrame("Screen");
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                for (int y = 0; y < screen.length; y++) {
                    for (int x = 0; x < screen[y].length; x++) {
                        g.setColor(screen[x][y]);
                        g.fillRect(x*10, y*10, 10, 10);
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
                    int R = DSC_D3.getValue();
                    int G = DSC_D4.getValue();
                    int B = DSC_D5.getValue();

                    screen[x][y] = new Color(R, G, B);

                    clearRegisters();
                }
            }
        };

        DSC_P1 = new Pin() {
            @Override
            public void onFire() {
                for (int y = 0; y < screen.length; y++) {
                    for (int x = 0; x < screen[y].length; x++) {
                        screen[y][x] = new Color(0, 0, 0);
                    }
                }
            }
        };

        DSC_P2 = new Pin() {
            @Override
            public void onFire() {
                panel.repaint();
            }
        };
    }

    @Override
    public void update() {

    }
}
