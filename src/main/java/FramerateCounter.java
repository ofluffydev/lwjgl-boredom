public class FramerateCounter {
    private static final long NANOSECONDS_PER_SECOND = 1000000000;
    private long lastTime;
    private int frames;
    private double fps;

    /**
     * Create a new FramerateCounter
     */
    public FramerateCounter() {
        this.lastTime = System.nanoTime();
        this.frames = 0;
    }

    /**
     * Update the frames per second counter
     */
    public void update() {
        long currentTime = System.nanoTime();
        long elapsedTimeNanos = currentTime - lastTime;
        if (elapsedTimeNanos >= NANOSECONDS_PER_SECOND) {
            double elapsedTimeSeconds = (double) elapsedTimeNanos / NANOSECONDS_PER_SECOND;
            fps = frames / elapsedTimeSeconds;
            System.out.printf("FPS: %.2f\n", fps);
            lastTime = currentTime;
            frames = 0;
        }
        frames++;
    }

    /**
     * Get the current frames per second
     * @return the current frames per second
     */
    public double getFps() {
        return fps;
    }
}