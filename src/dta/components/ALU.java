package dta.components;

import dta.Register;

public class ALU {

    public Register ALU_A = new Register(Register.WRITE_ONLY);
    public Register ALU_B = new Register(Register.WRITE_ONLY);
    public Register ALU_OP = new Register(Register.WRITE_ONLY);
    public Register ALU_OUT = new Register(Register.READ_ONLY);

    public void update(){
        int a = ALU_A.hardwareGetValue();
        int b = ALU_B.hardwareGetValue();

        switch (ALU_OP.hardwareGetValue()){
            case 0:
                ALU_OUT.hardwareSetValue(a + b);
                break;
            case 1:
                ALU_OUT.hardwareSetValue(a - b);
                break;
            case 2:
                ALU_OUT.hardwareSetValue(a * b);
                break;
            case 3:
                if (b != 0) {
                    ALU_OUT.hardwareSetValue(a / b);
                }
                else {
                    ALU_OUT.hardwareSetValue(0);
                }
                break;
            case 4:
                if (b != 0) {
                    ALU_OUT.hardwareSetValue(a % b);
                }
                else {
                    ALU_OUT.hardwareSetValue(0);
                }
                break;
            case 5:
                if (a > b) {
                    ALU_OUT.hardwareSetValue(1);
                }
                if (b > a) {
                    ALU_OUT.hardwareSetValue(2);
                } else {
                    ALU_OUT.hardwareSetValue(0);
                }
                break;
        }
    }
}
