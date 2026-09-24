package components;

public class RAM {
    public int[] values = new int[256];

    public Register RAM_R_ADDR = new Register(Register.WRITE_ONLY);
    public Register RAM_R_OUT = new Register(Register.READ_ONLY);

    public Register RAM_W_ADDR = new Register(Register.WRITE_ONLY);
    public Register RAM_W_VAL = new Register(Register.WRITE_ONLY);

    public Pin RAM_WRITE = new Pin() {
        @Override
        public void onFire() {
            int base = RAM_W_ADDR.getValue();
            if (base < values.length) values[base] = RAM_W_VAL.getValue();
        }
    };

    public void update(){
        int base = RAM_R_ADDR.getValue();
        if (base < values.length) RAM_R_OUT.setValue(values[base]);
    }
}
