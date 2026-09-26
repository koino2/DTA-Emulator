import assembler.Assembler;
import dta.Processor;
import dta.devices.ColorDisplayAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        int[] ROM = null; try {
            ROM = Assembler.assemble(Files.readString(Path.of("programs/gpu_colour_test.asm")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Processor processor = new Processor(ROM, 512);

        processor.dsc.devices.add(new ColorDisplayAdapter());

        processor.setClockSpeed(5000);
        processor.startClock();
    }
}
