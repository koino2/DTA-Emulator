package dta;

import dta.components.*;

import java.util.HashMap;
import java.util.Map;

public class Processor {

    public Memory memory;
    public ALU alu = new ALU();
    public RAM ram = new RAM();
    public ProgramCounter programCounter = new ProgramCounter();
    public DSC dsc = new DSC();

    int romSize = 512;
    public void setROMSize(int ROMSize){
        this.romSize = ROMSize;
        memory.ROMSize=romSize;
    }

    public Processor(int[] ROM){
        memory = new Memory(ROM, romSize);
    }

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

        memory.update();

        IR1.setValue(memory.R_OUT.getValue());
        IR2.setValue(memory.R_OUT1.getValue());
        IR3.setValue(memory.R_OUT2.getValue());

        memory.R_ADDR.setValue(temp);

        memory.update();

        execute();

        update();

        programCounter.PC.setValue(programCounter.PC.getValue()+3);
    }

    public void execute(){
        int instruction = IR1.getValue() & 0b11;

        if (instruction == 1){
            copy(IR2.getValue(), IR3.getValue());
        }
        else if (instruction == 2){
            set(IR2.getValue(), IR3.getValue());
        }
        else if (instruction == 3){
            pulse(IR2.getValue());
        }
    }

    public void copy(int regA, int regB){
        if (registers.size() > regA && registers.size() > regB){
            Register A = registers.get(regA);
            Register B = registers.get(regB);
            B.setValue(A.getValue());
        }
    }
    public void set(int reg, int value){
        if (registers.size() > reg){
            Register A = registers.get(reg);
            A.setValue(value);
        }
    }
    public void pulse(int pin){
        if (pins.size() > pin){
            Pin A = pins.get(pin);
            A.onFire();
        }
    }

    public void update(){
        memory.update();
        alu.update();
        ram.update();
        programCounter.update();
        dsc.update();
    }

    public Clock clock = new Clock() {
        @Override
        public void tick() {
            Processor.this.tick();
        }
    };
    public void setClockSpeed(int speed){clock.setClockSpeed(speed);}
    public void startClock(){clock.startClock();}
}
