package dta.devices;

import javax.swing.*;
import java.awt.*;

public class GPU extends Device{

    public boolean[][] screen = new boolean[64][64];

    public GPU(){
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
                        if (screen[x][y]){
                            g.setColor(Color.WHITE);
                        } else {
                            g.setColor(Color.BLACK);
                        }
                        g.fillRect(x*10, y*10, 10, 10);
                    }
                }
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
            int colour = DSC_D3.getValue();

            if (colour == 0) {
                screen[x][y] = false;
            } else if (colour == 1) {
                screen[x][y] = true;
            }

            clearRegisters();
        }
    }
}
