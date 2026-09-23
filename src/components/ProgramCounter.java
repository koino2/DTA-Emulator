package components;

public class ProgramCounter {

    public Register COUNT = new Register(Register.READ_WRITE);

    public Register VALUE = new Register(Register.WRITE_ONLY);
    public Register TARGET = new Register(Register.WRITE_ONLY);
    public Register JUMP_ADDR = new Register(Register.WRITE_ONLY);

    public Pin JUMP = new Pin() {
        @Override
        public void onFire() {
            if (VALUE.getValue() == TARGET.getValue()){
                COUNT.setValue(JUMP_ADDR.getValue());
            }
        }
    };

}
