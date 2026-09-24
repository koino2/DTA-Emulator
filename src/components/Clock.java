package components;

public class Clock {

    public int clockSpeed = 1_000;

    public double clockTime = (double) 1 /clockSpeed; // in seconds

    float nanosPerTick = (float) (clockTime * 1000000000);

    boolean running = false;

    long lastTickTime = 0;

    public void setClockSpeed(int clockSpeed){
        this.clockSpeed = clockSpeed;
        clockTime = (double) 1 / clockSpeed;
        nanosPerTick = (float) (clockTime * 1000000000);
    }

    public void startClock(){
        running = true;

        lastTickTime = System.nanoTime();

        Thread thread = new Thread(() -> {
            while (running){
                long timeNow = System.nanoTime();
                long timeSinceLastTick = timeNow-lastTickTime;
                float numberOfTicks = timeSinceLastTick / nanosPerTick;
                int ticks = (int) Math.floor(numberOfTicks);

                for (int i = 0; i < ticks; i++) {
                    tick();
                }

                if (ticks > 0){
                    lastTickTime += (long) (ticks * nanosPerTick);
                }
            }
        });
        thread.start();
    }

    public void tick(){

    }
}
