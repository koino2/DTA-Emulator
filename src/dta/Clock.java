package dta;

public abstract class Clock {

    public int clockSpeed = 1_000;

    public double clockTime = (double) 1 / clockSpeed; // in seconds

    float nanosPerTick = (float) (clockTime * 1000000000);

    public volatile boolean running = false;

    long lastTickTime = 0;

    public void setClockSpeed(int clockSpeed) {
        this.clockSpeed = clockSpeed;
        clockTime = (double) 1 / clockSpeed;
        nanosPerTick = (float) (clockTime * 1000000000);
    }

    public void initClock() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (!running) continue;
                long timeNow = System.nanoTime();
                long timeSinceLastTick = timeNow - lastTickTime;
                float numberOfTicks = timeSinceLastTick / nanosPerTick;
                int ticks = (int) Math.floor(numberOfTicks);

                //System.out.println("trying to tick");
                for (int i = 0; i < ticks; i++) {
                    tick();
                }

                if (ticks > 0) {
                    lastTickTime += (long) (ticks * nanosPerTick);
                }
            }
        });
        thread.start();

        System.out.println("clock initialized");
        initialized = true;
    }

    boolean initialized;

    public void startClock() {
        System.out.println(initialized);
        lastTickTime = System.nanoTime();
        running = true;
    }

    public void stopClock() {
        running = false;
    }

    public abstract void tick();
}
