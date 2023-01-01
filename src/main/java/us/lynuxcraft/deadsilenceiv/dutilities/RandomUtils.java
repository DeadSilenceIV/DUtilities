package us.lynuxcraft.deadsilenceiv.dutilities;

import java.util.Random;

public class RandomUtils {

    private static Random random;

    public static int getRandomIntBetween(int minimum, int maximum) {
        return getRandom().nextInt(maximum - minimum) + minimum;
    }

    public static double getRandomDoubleBetween(double minimum, double maximum) {
        return getRandom().nextDouble() * (maximum - minimum) + minimum;
    }

    public static Random getRandom(){
        if(random == null){
            random = new Random();
        }
        return random;
    }

}
