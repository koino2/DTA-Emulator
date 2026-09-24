package dta;

public class Processor {

    public void tick(){

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
