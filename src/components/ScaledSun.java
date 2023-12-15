package components;

import ressources.edu.princeton.cs.algs4.StdDraw;

public abstract class ScaledSun extends Sun {

    private static final double SIZE_SCALE = (double)1 / 100_000_000;

    /**
     * Draw the scaled sun.
     */
    public static void draw() {
        StdDraw.setPenColor(Sun.getDominantColor());
        StdDraw.filledCircle(0, 0, Sun.getRadius() * SIZE_SCALE);
    }

}
