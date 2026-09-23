package components;

public class Memory {

    public int[] values = new int[256];

    public Register R_ADDR = new Register(Register.WRITE_ONLY);
    public Register R_OUT = new Register(Register.READ_ONLY);

    public Register W_ADDR = new Register(Register.WRITE_ONLY);
    public Register W_VAL = new Register(Register.WRITE_ONLY);

    public Pin WRITE = new Pin() {
        @Override
        public void onFire() {
            int base = W_ADDR.getValue();
            if (base < values.length) values[base] = W_VAL.getValue();
        }
    };

    public void update(){
        int base = R_ADDR.getValue();
        if (base < values.length) R_OUT.setValue(values[base]);
    }

}
