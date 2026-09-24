import dta.Processor;

public class Main {
    public static void main(String[] args) {
        int[] ROM = new int[]{

        };

        Processor processor = new Processor(ROM, 512);
        processor.startClock();
    }
}
