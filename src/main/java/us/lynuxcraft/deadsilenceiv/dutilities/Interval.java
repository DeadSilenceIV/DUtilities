package us.lynuxcraft.deadsilenceiv.dutilities;

public interface Interval<N extends Number> {

    N getBottom();

    N getTop();

    boolean contains(N number);

    boolean isBottomOpened();

    boolean isTopOpened();

}
