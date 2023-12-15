package tools;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Clock {

    private static final long update_frequency_ms = 100;
    private static double seconds_for_one_real_second = 1;
    private static boolean running;
    private static long starting_timestamp;
    private static long current_timestamp;

    /**
     * Get the current date.
     * @return  The current date.
     */
    public static Date getCurrentDate() {
        return new Date(current_timestamp);
    }

    /**
     * Get the current date within a String.
     * @return  The current date.
     */
    public static String toISOString() {
        return Clock.getCurrentDate().toString();
    }

    /**
     * Get the number of days elapsed since the start of the clock.
     * @return The number of days elapsed since the start of the clock.
     */
    public static double daysSinceStarting() {
        return (double)(Clock.current_timestamp - Clock.starting_timestamp) / TimeUnit.DAYS.toMillis(1);
    }

    /**
     * Set the running state of the clock thread.
     * @param running   The new running state.
     */
    public static void setRunning(boolean running) {
        Clock.running = running;
    }

    /**
     * Set the clock speed scale.
     * @param real_duration     The real duration compared to the scaled one.
     * @param real_unit         The real unit compared to the scaled one.
     * @param scaled_duration   The scaled duration compared to the real one.
     * @param scaled_unit       The scaled unit compared to the real one.
     */
    public static void setScale(int real_duration, TimeUnit real_unit, int scaled_duration, TimeUnit scaled_unit) {
        Clock.seconds_for_one_real_second = (double)real_unit.toSeconds(real_duration) / scaled_unit.toSeconds(scaled_duration);
    }

    /**
     * Running script of the clock.
     */
    public static void run() {
        if (running) {
            throw new RuntimeException("Clock is already running");
        }

        Clock.setRunning(true);

        Clock.starting_timestamp = System.currentTimeMillis();
        Clock.current_timestamp = Clock.starting_timestamp;

        while (running) {
            try {
                Thread.sleep(Clock.update_frequency_ms);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted while sleeping");
            }

            double update_frequency_s = (double)Clock.update_frequency_ms / 1000;
            double seconds_to_increment = update_frequency_s * Clock.seconds_for_one_real_second;

            Clock.current_timestamp += (long)seconds_to_increment * 1000;

            System.out.println(toISOString());
        }
    }

}
