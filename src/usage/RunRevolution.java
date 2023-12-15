package usage;

import ressources.edu.princeton.cs.algs4.StdDraw;
import components.Planet;
import components.ScaledPlanet;
import components.ScaledSun;
import tools.Clock;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunRevolution {
    public static void main(String[] args) throws ClassNotFoundException {

        final int CANVAS_SIZE = 700;
        final double PLANET_SIZE_SCALE = (double)1 / 700_000;

        /* Source: https://nssdc.gsfc.nasa.gov/planetary/factsheet */
        List<Planet> planets = Arrays.asList(
                new Planet("Mercure", 4_879, (float)57.9, 88, new Color(135, 135, 135)),
                new Planet("Venus", 12_104, (float)108.2, (float)224.7, new Color(214, 200, 196)),
                new Planet("Terre", 12_756, (float)149.6, (float)365.2, new Color(3, 22, 57)),
                new Planet("Mars", 6_792, 228, 687, new Color(208, 100, 51)),
                new Planet("Jupiter", 142_984, (float)778.5, 4331, new Color(196, 148, 121)),
                new Planet("Saturne", 120_536, 1432, 10_747, new Color(119, 115, 77)),
                new Planet("Uranus", 51_118, 2867, 30_589, new Color(174, 224, 226)),
                new Planet("Neptune", 49_528, 4515, 59_800, new Color(214, 174, 241))
        );

        Planet farthestPlanet = Planet.getFarthest(planets);

        List<ScaledPlanet> scaledPlanets = new ArrayList<>();
        for (Planet planet : planets) {
            scaledPlanets.add(new ScaledPlanet(planet, (farthestPlanet.getDistanceToSun() + farthestPlanet.getRadius()), PLANET_SIZE_SCALE));
        }

        Clock.setScale(365, TimeUnit.DAYS, 10, TimeUnit.SECONDS);
        new Thread(Clock::run).start();

        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setScale(-1, 1);
        StdDraw.enableDoubleBuffering();

        ScaledSun.draw();

        while (true) {
            for (ScaledPlanet scaledPlanet : scaledPlanets) {
                scaledPlanet.clear();
                scaledPlanet.drawOrbit();
                scaledPlanet.draw();
                StdDraw.show();
            }

            StdDraw.pause(1000/24);
        }

    }
}
