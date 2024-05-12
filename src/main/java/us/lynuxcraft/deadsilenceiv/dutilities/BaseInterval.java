package us.lynuxcraft.deadsilenceiv.dutilities;

import lombok.Getter;

import java.util.Objects;

public abstract class BaseInterval<N extends Number> implements Interval<N> {
    @Getter protected final N bottom;
    @Getter protected final N top;
    @Getter protected final boolean bottomOpened;
    @Getter protected final boolean topOpened;
    public BaseInterval(N bottom, N top, boolean bottomOpened, boolean topOpened){
        this.bottom = bottom;
        this.top = top;
        this.bottomOpened = bottomOpened;
        this.topOpened = topOpened;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseInterval)) return false;
        BaseInterval<?> that = (BaseInterval<?>) o;
        return bottomOpened == that.bottomOpened && topOpened == that.topOpened && bottom.equals(that.bottom) && top.equals(that.top);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottom, top, bottomOpened, topOpened);
    }

}
