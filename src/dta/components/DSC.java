package dta.components;

import dta.devices.Device;
import dta.Pin;
import dta.Register;

import java.util.ArrayList;
import java.util.List;

public class DSC {

    public List<Device> devices = new ArrayList<>();

    public Register DSC_D0 = new Register(Register.READ_WRITE);
    public Register DSC_D1 = new Register(Register.READ_WRITE);
    public Register DSC_D2 = new Register(Register.READ_WRITE);
    public Register DSC_D3 = new Register(Register.READ_WRITE);
    public Register DSC_D4 = new Register(Register.READ_WRITE);
    public Register DSC_D5 = new Register(Register.READ_WRITE);
    public Register DSC_D6 = new Register(Register.READ_WRITE);
    public Register DSC_D7 = new Register(Register.READ_WRITE);

    public Register DSC_STATUS = new Register(Register.READ_ONLY);

    public Register DSC_DEVICE = new Register(Register.READ_WRITE);

    public Pin DSC_WRITE = new Pin() {
        @Override
        public void onFire() {
            if (devices.size() > DSC_DEVICE.hardwareGetValue()) {
                Device currentDevice = devices.get(DSC_DEVICE.hardwareGetValue());

                currentDevice.DSC_D0.hardwareSetValue(DSC_D0.hardwareGetValue());
                currentDevice.DSC_D1.hardwareSetValue(DSC_D1.hardwareGetValue());
                currentDevice.DSC_D2.hardwareSetValue(DSC_D2.hardwareGetValue());
                currentDevice.DSC_D3.hardwareSetValue(DSC_D3.hardwareGetValue());
                currentDevice.DSC_D4.hardwareSetValue(DSC_D4.hardwareGetValue());
                currentDevice.DSC_D5.hardwareSetValue(DSC_D5.hardwareGetValue());
                currentDevice.DSC_D6.hardwareSetValue(DSC_D6.hardwareGetValue());
                currentDevice.DSC_D7.hardwareSetValue(DSC_D7.hardwareGetValue());

                System.out.println(currentDevice.DSC_D0.hardwareGetValue() + " target " + DSC_D0.hardwareGetValue());
                System.out.println(currentDevice.DSC_D1.hardwareGetValue() + " target " + DSC_D1.hardwareGetValue());
                System.out.println(currentDevice.DSC_D2.hardwareGetValue() + " target " + DSC_D2.hardwareGetValue());
                System.out.println(currentDevice.DSC_D3.hardwareGetValue() + " target " + DSC_D3.hardwareGetValue());
                System.out.println(currentDevice.DSC_D4.hardwareGetValue() + " target " + DSC_D4.hardwareGetValue());
                System.out.println(currentDevice.DSC_D5.hardwareGetValue() + " target " + DSC_D5.hardwareGetValue());
                System.out.println(currentDevice.DSC_D6.hardwareGetValue() + " target " + DSC_D6.hardwareGetValue());
                System.out.println(currentDevice.DSC_D7.hardwareGetValue() + " target " + DSC_D7.hardwareGetValue());
            }
        }
    };

    public Pin DSC_D_WRITE = new Pin() {
        @Override
        public void onFire() {
            if (devices.size() > DSC_DEVICE.hardwareGetValue()){
                Device currentDevice = devices.get(DSC_DEVICE.hardwareGetValue());

                DSC_D0.hardwareSetValue(currentDevice.DSC_D0.hardwareGetValue());
                DSC_D1.hardwareSetValue(currentDevice.DSC_D1.hardwareGetValue());
                DSC_D2.hardwareSetValue(currentDevice.DSC_D2.hardwareGetValue());
                DSC_D3.hardwareSetValue(currentDevice.DSC_D3.hardwareGetValue());
                DSC_D4.hardwareSetValue(currentDevice.DSC_D4.hardwareGetValue());
                DSC_D5.hardwareSetValue(currentDevice.DSC_D5.hardwareGetValue());
                DSC_D6.hardwareSetValue(currentDevice.DSC_D6.hardwareGetValue());
                DSC_D7.hardwareSetValue(currentDevice.DSC_D7.hardwareGetValue());

                DSC_STATUS.hardwareSetValue(currentDevice.DSC_STATUS.hardwareGetValue());
            }
        }
    };

    public void update(){
        for (Device device : devices){
            device.update();
        }
    }

}
