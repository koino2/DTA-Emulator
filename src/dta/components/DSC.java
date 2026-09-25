package dta.components;

import dta.devices.Device;
import dta.Pin;
import dta.Register;

import java.util.ArrayList;
import java.util.List;

public class DSC {

    public List<Device> devices = new ArrayList<>();
    public Device currentDevice;

    public Register DSC_D0 = connect(0);
    public Register DSC_D1 = connect(1);
    public Register DSC_D2 = connect(2);
    public Register DSC_D3 = connect(3);
    public Register DSC_D4 = connect(4);
    public Register DSC_D5 = connect(5);
    public Register DSC_D6 = connect(6);
    public Register DSC_D7 = connect(7);

    public Register DSC_STATUS = new Register(Register.READ_ONLY);

    public Register DSC_DEVICE = new Register(Register.READ_WRITE);

    public Pin DSC_P0 = connectPin(0);
    public Pin DSC_P1 = connectPin(1);
    public Pin DSC_P2 = connectPin(2);
    public Pin DSC_P3 = connectPin(3);
    public Pin DSC_P4 = connectPin(4);

    public void update(){
        if (DSC_DEVICE.getValue() < devices.size()){
            currentDevice = devices.get(DSC_DEVICE.getValue());
        } else {
            currentDevice = null;
        }

        for (Device device : devices){
            device.update();
        }
    }

    public Register getData(int index){
        if (currentDevice != null) {
            switch (index) {
                case 0:
                    return currentDevice.DSC_D0;
                case 1:
                    return currentDevice.DSC_D1;
                case 2:
                    return currentDevice.DSC_D2;
                case 3:
                    return currentDevice.DSC_D3;
                case 4:
                    return currentDevice.DSC_D4;
                case 5:
                    return currentDevice.DSC_D5;
                case 6:
                    return currentDevice.DSC_D6;
                case 7:
                    return currentDevice.DSC_D7;
            }
        }
        return null;
    }

    Register connect(int index){
        return new Register(Register.READ_WRITE){
            @Override
            public int getValue(){
                if (currentDevice == null){
                    return 0;
                }
                return getData(index).getValue();
            }
            @Override
            public void setValue(int value){
                if (currentDevice != null){
                    getData(index).setValue(value);
                }
            }
        };
    }

    public Pin getPin(int index){
        if (currentDevice != null) {
            switch (index) {
                case 0:
                    return currentDevice.DSC_P0;
                case 1:
                    return currentDevice.DSC_P1;
                case 2:
                    return currentDevice.DSC_P2;
                case 3:
                    return currentDevice.DSC_P3;
                case 4:
                    return currentDevice.DSC_P4;
            }
        }
        return null;
    }

    Pin connectPin(int index){
        return new Pin() {
            @Override
            public void onFire() {
                getPin(index).onFire();
            }
        };
    }

}
