package us.lynuxcraft.deadsilenceiv.dutilities;

import java.util.Random;

public class RandomUtils {

    private static Random random = new Random();

    public static int getRandomIntBetween(int minimum, int maximum) {
        return random.nextInt(maximum - minimum) + minimum;
    }

    public static double getRandomDoubleBetween(double minimum, double maximum) {
        return random.nextDouble() * (maximum - minimum) + minimum;
    }

}
