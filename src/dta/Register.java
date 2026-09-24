package dta;

public class Register {

    private int value;

    public static final int READ_WRITE = 0;
    public static final int READ_ONLY = 1;
    public static final int WRITE_ONLY = 2;
    public int type = READ_WRITE;

    public Register(){}

    public Register(int type){
        this.type = type;
    }

    public Register(int type, int value){
        this.type = type;
        this.value = value;
    }

    public void setValue(int value){
        if (type == WRITE_ONLY || type == READ_WRITE) {
            this.value = value;
        } else {
            System.out.println("WARNING: Write to a non-writable register");
        }
    }

    public int getValue(){
        if (type == READ_ONLY || type == READ_WRITE) {
            return value;
        } else {
            System.out.println("WARNING: Read from a non-readable register");
        }
        return 0;
    }

    public void hardwareSetValue(int value){
        this.value = value;
    }

    public int hardwareGetValue(){
        return value;
    }
}
