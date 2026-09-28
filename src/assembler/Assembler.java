package assembler;

import java.util.*;

public class Assembler {

    public static Map<String, Integer> registers = new HashMap<>();
    public static Map<String, Integer> pins = new HashMap<>();
    public static Map<String, Integer> aluOps = new HashMap<>();

    static {
        registers.put("R_ADDR", 0);
        registers.put("R_OUT", 1);
        registers.put("R_OUT1", 2);
        registers.put("R_OUT2", 3);
        registers.put("W_ADDR", 4);
        registers.put("W_VAL", 5);

        registers.put("ALU_A", 6);
        registers.put("ALU_B", 7);
        registers.put("ALU_OP", 8);
        registers.put("ALU_OUT", 9);

        registers.put("RAM_R_ADDR", 10);
        registers.put("RAM_R_OUT", 11);
        registers.put("RAM_W_ADDR", 12);
        registers.put("RAM_W_VAL", 13);

        registers.put("PC", 14);
        registers.put("PC_VALUE", 15);
        registers.put("PC_TARGET", 16);
        registers.put("PC_JMP", 17);

        registers.put("DSC_D0", 18);
        registers.put("DSC_D1", 19);
        registers.put("DSC_D2", 20);
        registers.put("DSC_D3", 21);
        registers.put("DSC_D4", 22);
        registers.put("DSC_D5", 23);
        registers.put("DSC_D6", 24);
        registers.put("DSC_D7", 25);
        registers.put("DSC_STATUS", 26);
        registers.put("DSC_DEVICE", 27);

        pins.put("WRITE", 0);
        pins.put("RAM_WRITE", 1);
        pins.put("JUMP", 2);
        pins.put("DSC_P0", 3);
        pins.put("DSC_P1", 4);
        pins.put("DSC_P2", 5);
        pins.put("DSC_P3", 6);
        pins.put("DSC_P4", 7);

        aluOps.put("ADD", 0);
        aluOps.put("SUB", 1);
        aluOps.put("MUL", 2);
        aluOps.put("DIV", 3);
        aluOps.put("MOD", 4);
    }

    public static int[] assemble (String assembly){
        Map<String, Integer> markers = new HashMap<>();
        Map<String, Integer> consts = new HashMap<>();

        String[] lines = assembly.split("\\R");

        int address = 0;
        for (String l : lines){

            String line = cleanLine(l);
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("mark")){

                if (parts.length != 2){System.out.println("Invalid marker: "+line);}

                String marker = parts[1];

                if (markers.containsKey(marker)){System.out.println("Duplicate marker: "+line);}

                markers.put(marker.toUpperCase(), address);
            } else {
                address += 3;
            }

        }

        for (String l : lines){

            String line = cleanLine(l);
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("const")){

                if (parts.length != 3){System.out.println("Invalid constant: "+line);}

                String name = parts[1];

                if (consts.containsKey(name)){System.out.println("Duplicate constant: "+line);}

                int value = resolveValue(parts[2], markers, consts);

                consts.put(name.toUpperCase(), value);
            }

        }

        List<Integer> output = new ArrayList<>();

        for (String l : lines){
            String line = cleanLine(l);
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("mark")) continue;

            String instruction = parts[0].toUpperCase();
            switch (instruction){
                case "NOP":
                    if (parts.length != 1){
                        System.out.println("NOP takes no arguments: "+line);
                    }
                    output.add(0);
                    output.add(0);
                    output.add(0);
                    break;
                case "COPY":
                    if (parts.length != 3){
                        System.out.println("Invalid COPY statement: "+line);
                    }
                    output.add(1);
                    output.add(getRegister(parts[1]));
                    output.add(getRegister(parts[2]));
                    break;
                case "SET":
                    if (parts.length != 3){
                        System.out.println("Invalid SET statement: "+line);
                    }
                    output.add(2);
                    output.add(getRegister(parts[1]));
                    output.add(resolveValue(parts[2], markers, consts));
                    break;
                case "PULSE":
                    if (parts.length != 2){
                        System.out.println("Invalid PULSE statement: "+line);
                    }
                    output.add(3);
                    output.add(getPin(parts[1]));
                    output.add(0);
            }
        }

        int[] assembled = new int[output.size()];
        for (int i = 0; i < assembled.length; i++) {
            assembled[i] = output.get(i);
        }

        return assembled;
    }

    public static String cleanLine(String line){
        int commentIndex = line.indexOf("#");
        if (commentIndex >= 0){
            line = line.substring(0, commentIndex);
        }
        line = line.replace(";", "");
        line = line.replace(",", "");

        return line.trim();
    }

    public static int getRegister(String register){
        String token = register.toUpperCase();
        if (!registers.containsKey(token)){
            System.out.println("Invalid register: "+register);
            return 0;
        }
        return registers.get(token);
    }

    public static int getPin(String pin){
        String token = pin.toUpperCase();
        if (!pins.containsKey(token)){
            System.out.println("Invalid pin: "+pin);
            return 0;
        }
        return pins.get(token);
    }

    public static int resolveValue(String token, Map<String, Integer> labels, Map<String, Integer> consts){
        token = token.toUpperCase().strip();

        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {

        }

        if (labels.containsKey(token)){
            return labels.get(token) - 3;
        }

        if (consts.containsKey(token)){
            return labels.get(token) - 3;
        }

        if (aluOps.containsKey(token)){
            return aluOps.get(token);
        }

        System.out.println("Invalid value: "+token);

        return 0;
    }
}
