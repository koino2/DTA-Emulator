package dta.devices;

import dta.Pin;
import dta.Register;

public abstract class Device {

    public final Register DSC_D0 = new Register(Register.READ_WRITE);
    public final Register DSC_D1 = new Register(Register.READ_WRITE);
    public final Register DSC_D2 = new Register(Register.READ_WRITE);
    public final Register DSC_D3 = new Register(Register.READ_WRITE);
    public final Register DSC_D4 = new Register(Register.READ_WRITE);
    public final Register DSC_D5 = new Register(Register.READ_WRITE);
    public final Register DSC_D6 = new Register(Register.READ_WRITE);
    public final Register DSC_D7 = new Register(Register.READ_WRITE);

    public final Register DSC_STATUS = new Register(Register.READ_ONLY);

    public Pin DSC_P0 = new Pin(){@Override public void onFire(){}};
    public Pin DSC_P1 = new Pin(){@Override public void onFire(){}};
    public Pin DSC_P2 = new Pin(){@Override public void onFire(){}};
    public Pin DSC_P3 = new Pin(){@Override public void onFire(){}};
    public Pin DSC_P4 = new Pin(){@Override public void onFire(){}};

    public abstract void update();

}
