package dta.gui;

import dta.devices.ColorDisplayAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GPUInterface extends JPanel {
    ColorDisplayAdapter cda = new ColorDisplayAdapter(false); // shhhh don't tell anybody that it's not a GPU and only a display adapter

    @Override
    protected void paintComponent(Graphics g){
        int width = getWidth();
        int height = getHeight();
        int maxDim = Math.max(width, height);
        int centerX = width/2 - maxDim/2;
        int centerY = height/2 - maxDim/2;
        BufferedImage img = new BufferedImage(cda.screen[0].length, cda.screen.length, BufferedImage.TYPE_INT_ARGB);
        Graphics imgGraphics = img.createGraphics();
        for (int y = 0; y < cda.screen.length; y++) {
            for (int x = 0; x < cda.screen[y].length; x++) {
                imgGraphics.setColor(cda.screen[x][y]);
                imgGraphics.fillRect(x, y, 1, 1);
            }
        }
        g.drawImage(img, centerX, centerY, maxDim, maxDim, null);
    }
}
