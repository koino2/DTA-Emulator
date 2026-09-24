import dta.Processor;
import dta.devices.GPU;

public class Main {
    public static void main(String[] args) {
        int[] ROM = new int[]{0, 0, 0, 2, 1, 0, 2, 14, 0};

        Processor processor = new Processor(ROM, 512);

        processor.dsc.devices.add(new GPU());

        processor.setClockSpeed(2);
        processor.startClock();
    }
}
