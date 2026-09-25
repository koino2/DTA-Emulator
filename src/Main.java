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
                2 4 512
                2 5 -2
                3 0 0
                2 4 513
                2 5 0
                3 0 0
                2 0 512
                1 1 6
                2 7 2
                1 9 5
                2 4 512
                3 0 0
                2 0 512
                1 1 15
                2 16 64
                2 17 90
                3 2 0
                2 16 63
                2 17 102
                3 2 0
                2 0 512
                1 1 19
                2 0 513
                1 1 20
                2 21 1
                2 18 1
                3 3 0
                2 14 21
                2 4 512
                2 5 1
                3 0 0
                2 14 108
                2 4 512
                2 5 0
                3 0 0
                2 0 513
                1 1 6
                2 7 1
                1 9 5
                2 4 513
                3 0 0
                2 14 63
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

        processor.setClockSpeed(500);
        processor.startClock();
    }
}
