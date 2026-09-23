package components;

public class ALU {

    public Register ALU_A = new Register(Register.WRITE_ONLY);
    public Register ALU_B = new Register(Register.WRITE_ONLY);
    public Register ALU_OP = new Register(Register.WRITE_ONLY);
    public Register ALU_OUT = new Register(Register.READ_ONLY);

    public void update(){
        int a = ALU_A.getValue();
        int b = ALU_B.getValue();

        switch (ALU_OP.getValue()){
            case 0:
                ALU_OUT.setValue(a + b);
            case 1:
                ALU_OUT.setValue(a - b);
            case 2:
                ALU_OUT.setValue(a * b);
            case 3:
                ALU_OUT.setValue(a / b);
            case 4:
                ALU_OUT.setValue(a % b);
        }
    }
}
