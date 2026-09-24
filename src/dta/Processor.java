package dta;

import dta.components.*;

import java.util.HashMap;
import java.util.Map;

public class Processor {

    public Memory memory = new Memory();
    public ALU alu = new ALU();
    public RAM ram = new RAM();
    public ProgramCounter programCounter = new ProgramCounter();
    public DSC dsc = new DSC();

    final Map<Integer, Register> registers = new HashMap<>();
    {
        registers.put(0, memory.R_ADDR);
        registers.put(1, memory.R_OUT);
        registers.put(2, memory.R_OUT1);
        registers.put(3, memory.R_OUT2);
        registers.put(4, memory.W_ADDR);
        registers.put(5, memory.W_VAL);

        registers.put(6, alu.ALU_A);
        registers.put(7, alu.ALU_B);
        registers.put(8, alu.ALU_OP);
        registers.put(9, alu.ALU_OUT);

        registers.put(10, ram.RAM_R_ADDR);
        registers.put(11, ram.RAM_R_OUT);
        registers.put(12, ram.RAM_W_ADDR);
        registers.put(13, ram.RAM_W_VAL);

        registers.put(14, programCounter.PC);
        registers.put(15, programCounter.PC_VALUE);
        registers.put(16, programCounter.PC_TARGET);
        registers.put(17, programCounter.PC_JMP);

        registers.put(18, dsc.DSC_D0);
        registers.put(19, dsc.DSC_D1);
        registers.put(20, dsc.DSC_D2);
        registers.put(21, dsc.DSC_D3);
        registers.put(22, dsc.DSC_D4);
        registers.put(23, dsc.DSC_D5);
        registers.put(24, dsc.DSC_D6);
        registers.put(25, dsc.DSC_D7);
        registers.put(26, dsc.DSC_STATUS);
        registers.put(27, dsc.DSC_DEVICE);
    }

    final Map<Integer, Pin> pins = new HashMap<>();
    {
        pins.put(0, memory.WRITE);
        pins.put(1, ram.RAM_WRITE);
        pins.put(2, programCounter.JUMP);
        pins.put(3, dsc.DSC_WRITE);
    }
    
    public Register IR1 = new Register();
    public Register IR2 = new Register();
    public Register IR3 = new Register();

    public void tick(){
        int temp = memory.R_ADDR.getValue();

        memory.R_ADDR.setValue(programCounter.PC.getValue());

        IR1.setValue(memory.R_OUT.getValue());
        IR2.setValue(memory.R_OUT1.getValue());
        IR3.setValue(memory.R_OUT2.getValue());

        memory.R_ADDR.setValue(temp);

        // execute

        programCounter.PC.setValue(programCounter.PC.getValue()+3);
    }

    public Clock clock = new Clock() {
        @Override
        public void tick() {
            tick();
        }
    };
    public void setClockSpeed(int speed){clock.setClockSpeed(speed);}
    public void startClock(){clock.startClock();}
}
