package dta.components;

import dta.Pin;
import dta.Register;

public class Memory {

    public int[] values = new int[256];

    public Register R_ADDR = new Register(Register.WRITE_ONLY);
    public Register R_OUT = new Register(Register.READ_ONLY);
    public Register R_OUT1 = new Register(Register.READ_ONLY);
    public Register R_OUT2 = new Register(Register.READ_ONLY);

    public Register W_ADDR = new Register(Register.WRITE_ONLY);
    public Register W_VAL = new Register(Register.WRITE_ONLY);

    public int[] ROM;
    public int ROMSize;

    public Memory(int[] ROM, int ROMSize){
        this.ROM = ROM;
        this.ROMSize = ROMSize;
    }

    public Pin WRITE = new Pin() {
        @Override
        public void onFire() {
            int base = W_ADDR.getValue();
            if (base < values.length) values[base] = W_VAL.getValue();
        }
    };

    public void update(){
        int base = R_ADDR.getValue();

        if (base < values.length) {
            if (base >= ROMSize) {
                R_OUT.setValue(values[base]);
            }
            else {
                if (ROM.length > base) {
                    R_OUT.setValue(ROM[base]);
                } else R_OUT.setValue(0);
            }
        }

        if (base+1 < values.length) {
            if (base+1 >= ROMSize) {
                R_OUT.setValue(values[base+1]);
            }
            else {
                if (ROM.length > base) {
                    R_OUT.setValue(ROM[base+1]);
                } else R_OUT.setValue(0);
            }
        }

        if (base+2 < values.length) {
            if (base+2 >= ROMSize) {
                R_OUT.setValue(values[base+2]);
            }
            else {
                if (ROM.length > base) {
                    R_OUT.setValue(ROM[base+2]);
                } else R_OUT.setValue(0);
            }
        }
    }

}
