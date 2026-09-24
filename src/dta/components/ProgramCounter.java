package dta.components;

import dta.Pin;
import dta.Register;

public class ProgramCounter {

    public Register PC = new Register(Register.READ_WRITE);

    public Register PC_VALUE = new Register(Register.WRITE_ONLY);
    public Register PC_TARGET = new Register(Register.WRITE_ONLY);
    public Register PC_JMP = new Register(Register.WRITE_ONLY);

    public Pin JUMP = new Pin() {
        @Override
        public void onFire() {
            if (PC_VALUE.getValue() == PC_TARGET.getValue()){
                PC.setValue(PC_JMP.getValue());
            }
        }
    };

    public void update(){

    }

}
