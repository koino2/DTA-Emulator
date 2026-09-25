package dta.devices;

import javax.swing.*;
import java.awt.*;

public class ColorDisplayAdapter extends Device{

    public Color[][] screen = new Color[64][64];

    public ColorDisplayAdapter(){
        JFrame j = new JFrame("Screen");
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.setSize(640, 640);
        j.setLocationRelativeTo(null);
        j.setVisible(true);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                for (int y = 0; y < screen.length; y++) {
                    for (int x = 0; x < screen[y].length; x++) {
                        g.setColor(screen[x][y]);
                        g.fillRect(x*10, y*10, 10, 10);
                    }
                }
                repaint();
            }
        };
        j.setContentPane(panel);
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

    @Override
    public void update() {
        if (DSC_D0.getValue() == 1){
            int x = DSC_D1.getValue();
            int y = DSC_D2.getValue();
            int R = DSC_D3.getValue();
            int G = DSC_D4.getValue();
            int B = DSC_D5.getValue();

            screen[x][y] = new Color(R, G, B);

            clearRegisters();
        }
    }
}
