package dta.devices;

import dta.Pin;
import dta.Processor;

public class PowerController extends Device{

    public Processor processor;

    public PowerController(Processor processor){
        this.processor = processor;

        DSC_P0 = new Pin() {
            @Override
            public void onFire() {
                send();
            }
        };
    }

    void send(){
        if (DSC_D0.getValue() == 1){
            processor.setClockSpeed(DSC_D1.getValue());
        }
        if (DSC_D0.getValue() == 2){
            DSC_D1.setValue(processor.clock.clockSpeed);
        }
    }

    @Override
    public void update() {

    }
}
