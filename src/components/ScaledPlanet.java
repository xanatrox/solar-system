package components;

import ressources.edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;

public final class ScaledPlanet extends Planet {

    private final double farthest_distance_to_sun;
    private final double size_scale;

    /**
     * Create a scaled planet.
     * @param planet                    The planet to scale.
     * @param farthest_distance_to_sun  The farthest distance to the sun.
     * @param size_scale                The scale to apply for planet size.
     */
    public ScaledPlanet(Planet planet, double farthest_distance_to_sun, double size_scale) {
        super(planet);
        this.farthest_distance_to_sun = farthest_distance_to_sun;
        this.size_scale = size_scale;
    }

    /**
     * Get the scaled radius of the planet.
     * @return  The scaled radius of the planet.
     */
    public double getScaledRadius() {
        return super.getRadius() * size_scale;
    }

    /**
     * Get the scaled distance from the sun.
     * @return  The scaled distance from the sun.
     */
    public double getScaledDistanceToSun() {
        return super.getDistanceToSun() / this.farthest_distance_to_sun;
    }

    /**
     * Get the scaled abscissa of the planet.
     * @return  The current scaled abscissa of the planet.
     */
    public double getScaledX() {
        return super.getX() / this.farthest_distance_to_sun;
    }

    /**
     * Get the scaled ordinate of the planet.
     * @return  The current scaled ordinate of the planet.
     */
    public double getScaledY() {
        return super.getY() / this.farthest_distance_to_sun;
    }

    /**
     * Draw the orbit of the scaled planet.
     */
    public void drawOrbit() {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.circle(0, 0, this.getScaledDistanceToSun());

        if (this.getScaledDistanceToSun() > 0.1) {
            StdDraw.text(0, -this.getScaledDistanceToSun() + StdDraw.getFont().getSize2D() / 700, super.getName());
        }
    }

    /**
     * Draw the scaled planet.
     */
    public void draw() {
        StdDraw.setPenColor(this.getDominantColor());
        StdDraw.filledCircle(this.getScaledX(), this.getScaledY(), this.getScaledRadius());
    }

    /**
     * Clear the scaled planet and it's orbit.
     */
    public void clear() {
        StdDraw.setPenRadius(this.getScaledRadius() * 2);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.circle(0, 0, this.getScaledDistanceToSun());
    }

}
