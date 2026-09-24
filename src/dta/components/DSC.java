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
            if (devices.size() > DSC_DEVICE.getValue()) {
                Device currentDevice = devices.get(DSC_DEVICE.getValue());

                currentDevice.DSC_D0.setValue(DSC_D0.getValue());
                currentDevice.DSC_D1.setValue(DSC_D1.getValue());
                currentDevice.DSC_D2.setValue(DSC_D2.getValue());
                currentDevice.DSC_D3.setValue(DSC_D3.getValue());
                currentDevice.DSC_D4.setValue(DSC_D4.getValue());
                currentDevice.DSC_D5.setValue(DSC_D5.getValue());
                currentDevice.DSC_D6.setValue(DSC_D6.getValue());
                currentDevice.DSC_D7.setValue(DSC_D7.getValue());
            }
        }
    };

    public void update(){
        if (devices.size() > DSC_DEVICE.getValue()){
            Device currentDevice = devices.get(DSC_DEVICE.getValue());

            DSC_D0.setValue(currentDevice.DSC_D0.getValue());
            DSC_D1.setValue(currentDevice.DSC_D1.getValue());
            DSC_D2.setValue(currentDevice.DSC_D2.getValue());
            DSC_D3.setValue(currentDevice.DSC_D3.getValue());
            DSC_D4.setValue(currentDevice.DSC_D4.getValue());
            DSC_D5.setValue(currentDevice.DSC_D5.getValue());
            DSC_D6.setValue(currentDevice.DSC_D6.getValue());
            DSC_D7.setValue(currentDevice.DSC_D7.getValue());

            DSC_STATUS.setValue(currentDevice.DSC_STATUS.getValue());
        }

        for (Device device : devices){
            device.update();
        }
    }

}
