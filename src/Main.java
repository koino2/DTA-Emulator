import dta.Processor;
import dta.devices.GPU;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String program = """
                0 0 0
                2 27 0
                2 18 1
                2 19 5
                2 20 5
                2 21 1
                3 3 0
        """;

        Scanner scanner = new Scanner(program);
        List<Integer> bytes = new ArrayList<>();
        while (scanner.hasNext()){
            bytes.add(scanner.nextInt());
        }

        int[] ROM = new int[bytes.size()];
        for (int i = 0; i < ROM.length; i++) {
            ROM[i] = bytes.get(i);
        }

        Processor processor = new Processor(ROM, 512);

        processor.dsc.devices.add(new GPU());

        processor.setClockSpeed(5);
        processor.startClock();
    }
}
