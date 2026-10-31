package dta.gui;

import dta.devices.ColorDisplayAdapter;
import dta.devices.Device;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GPUInterface extends DeviceInterface {
    ColorDisplayAdapter cda; // shhhh don't tell anybody that it's not a GPU and only a display adapter

    public GPUInterface(ColorDisplayAdapter device) {
        super(device);
        cda = device;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int minDim = Math.min(width, height);
        int centerX = width/2 - minDim/2;
        int centerY = height/2 - minDim/2;
        BufferedImage img = new BufferedImage(cda.screen[0].length, cda.screen.length, BufferedImage.TYPE_INT_ARGB);
        Graphics imgGraphics = img.createGraphics();
        for (int y = 0; y < cda.screen.length; y++) {
            for (int x = 0; x < cda.screen[y].length; x++) {
                imgGraphics.setColor(cda.screen[y][x]);
                imgGraphics.fillRect(x, y, 1, 1);
            }
        }
        //g.drawImage(img, 100, 0, width, height, null);
        g.drawImage(img, centerX, centerY, minDim, minDim, null);
    }
}
