package dta.gui;

import dta.devices.Device;

import javax.swing.*;

public class DeviceInterface extends JPanel {
    public Device device;
    public DeviceInterface(Device device){
        this.device = device;
    }
}
