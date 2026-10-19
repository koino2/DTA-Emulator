import assembler.Assembler;
import dta.Processor;
import dta.devices.ColorDisplayAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] ROM = null; try {
            ROM = Assembler.assemble(
                    Files.readString(Path.of("../DTA-CPU/programs/3d_cube/comb.asm"))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Processor processor = new Processor(ROM, 3000);

        processor.dsc.devices.add(new ColorDisplayAdapter());

        Thread.sleep(1000);

        processor.setClockSpeed(100);
        processor.clock.initClock();
        processor.startClock();
    }
}
