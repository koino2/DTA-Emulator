package dta;

import java.util.HashMap;
import java.util.Map;

public class ValueConverter {
    public static Map<Integer, String> _registers = new HashMap<>();
    public static Map<Integer, String> _pins = new HashMap<>();

    static {
        _registers.put(0, "R_ADDR");
        _registers.put(1, "R_OUT");
        _registers.put(2, "R_OUT1");
        _registers.put(3, "R_OUT2");
        _registers.put(4, "W_ADDR");
        _registers.put(5, "W_VAL");

        _registers.put(6, "ALU_A");
        _registers.put(7, "ALU_B");
        _registers.put(8, "ALU_OP");
        _registers.put(9, "ALU_OUT");

        _registers.put(10, "RAM_R_ADDR");
        _registers.put(11, "RAM_R_OUT");
        _registers.put(12, "RAM_W_ADDR");
        _registers.put(13, "RAM_W_VAL");

        _registers.put(14, "PC");
        _registers.put(15, "PC_VALUE");
        _registers.put(16, "PC_TARGET");
        _registers.put(17, "PC_JMP");

        _registers.put(18, "DSC_D0");
        _registers.put(19, "DSC_D1");
        _registers.put(20, "DSC_D2");
        _registers.put(21, "DSC_D3");
        _registers.put(22, "DSC_D4");
        _registers.put(23, "DSC_D5");
        _registers.put(24, "DSC_D6");
        _registers.put(25, "DSC_D7");
        _registers.put(26, "DSC_STATUS");
        _registers.put(27, "DSC_DEVICE");

        _pins.put(0, "WRITE");
        _pins.put(1, "RAM_WRITE");
        _pins.put(2, "JUMP");
        _pins.put(3, "DSC_P0");
        _pins.put(4, "DSC_P1");
        _pins.put(5, "DSC_P2");
        _pins.put(6, "DSC_P3");
        _pins.put(7, "DSC_P4");
    }

    public static String getRegister(int register){
        return _registers.get(register);
    }

    public static String getPin(int pin){
        return _pins.get(pin);
    }
}
