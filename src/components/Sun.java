package components;

import java.awt.Color;

public abstract class Sun {

    private static final String name = "Soleil";
    private static final double diameter = 1_392_700;
    private static final Color dominant_color = new Color(247, 148, 71);

    /**
     * Get the name of the sun.
     * @return  The name of the sun.
     */
    public static String getDisplayName() {
        return name;
    }

    /**
     * Get the radius of the sun.
     * @return  The radius of the sun.
     */
    public static double getRadius() {
        return diameter / 2;
    }

    /**
     * Get the color of the sun.
     * @return  The color of the sun.
     */
    public static Color getDominantColor() {
        return dominant_color;
    }

}
