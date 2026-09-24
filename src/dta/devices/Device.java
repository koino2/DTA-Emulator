package dta.devices;

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

    public abstract void update();

}
