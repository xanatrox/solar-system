package components;

import tools.Clock;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Planet extends Thread implements InstantiableCelestialBody {

    private final double diameter;
    private final double distance_to_sun;
    private final double orbital_period;
    private final Color dominant_color;
    private double current_degree;
    private boolean running;

    /**
     * Create a new planet.
     * @param name              The name of the planet.
     * @param diameter          The diameter of the planet in millions of kilometers (km).
     * @param distance_to_sun   The distance from the sun in kilometers (10^6 km).
     * @param orbital_period    The time it takes to orbit the sun in days (d).
     * @param dominant_color    The dominant color of the planet.
     */
    public Planet (String name, double diameter, double distance_to_sun, double orbital_period, Color dominant_color) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (diameter <= 0) {
            throw new IllegalArgumentException("Diameter cannot be negative");
        }

        if (distance_to_sun <= 0) {
            throw new IllegalArgumentException("Distance to sun cannot be negative");
        }

        if (orbital_period <= 0) {
            throw new IllegalArgumentException("Orbital period cannot be negative");
        }

        if (dominant_color == null) {
            throw new IllegalArgumentException("Dominant color cannot be null");
        }

        this.setName(name);
        this.diameter = diameter;
        this.distance_to_sun = distance_to_sun;
        this.orbital_period = orbital_period;
        this.dominant_color = dominant_color;

        this.current_degree = 0;
        this.running = true;

        this.start();
    }

    /**
     * Create a copy of the planet.
     * @param planet    The planet to copy.
     */
    public Planet(Planet planet) throws NullPointerException {
        this(
                planet.getName(),
                planet.getRadius(),
                planet.getDistanceToSun() * Math.pow(10, -6),
                planet.getOrbitalPeriod(),
                planet.getDominantColor()
        );
    }

    /**
     * Get the radius of the planet.
     * @return  The radius of the planet in kilometers (km).
     */
    public double getRadius() {
        return this.diameter / 2;
    }

    /**
     * Get the distance from the sun.
     * @return  The distance from the sun in kilometers (km).
     */
    public double getDistanceToSun() {
        return this.distance_to_sun * Math.pow(10, 6);
    }

    /**
     * Get the orbital period.
     * @return  The orbital period in days (d).
     */
    public double getOrbitalPeriod() {
        return this.orbital_period;
    }

    /**
     * Get the dominant color of the planet.
     * @return  The dominant color of the planet.
     */
    public Color getDominantColor() {
        return this.dominant_color;
    }

    /**
     * Get the abscissa of the planet.
     * @return  The current abscissa of the planet in kilometers (km).
     */
    public double getX() {
        return this.getDistanceToSun() * Math.sin(this.current_degree / 360 * Math.PI * 2);
    }

    /**
     * Get the ordinate of the planet.
     * @return  The current ordinate of the planet in kilometers (km).
     */
    public double getY() {
        return this.getDistanceToSun() * Math.cos(this.current_degree / 360 * Math.PI * 2);
    }

    /**
     * Set the running state of the planet thread.
     * @param running   The new running state.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Running script of the planet.
     */
    public void run() {
        while (running) {
            this.current_degree = 360 * (Clock.daysSinceStarting() % this.getOrbitalPeriod()) / this.getOrbitalPeriod();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted while sleeping");
            }
        }
    }

    /**
     * Get the farthest planet from the sun.
     * @param planets   The list of planets.
     * @return          The planet farthest from the sun
     */
    public static Planet getFarthest(List<Planet> planets) {
        return Collections.max(planets, Comparator.comparing(Planet::getDistanceToSun));
    }

}
