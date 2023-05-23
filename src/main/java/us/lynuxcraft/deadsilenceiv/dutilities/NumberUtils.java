package us.lynuxcraft.deadsilenceiv.dutilities;

public class NumberUtils{

    public static long roundDown(long number, long multiple) {
        return number >= 0 ? (number / multiple) * multiple : ((number - multiple + 1) / multiple) * multiple;
    }

    public static long roundUp(long number, long multiple) {
        return number >= 0 ? ((number + multiple - 1) / multiple) * multiple : (number / multiple) * multiple;
    }

}
