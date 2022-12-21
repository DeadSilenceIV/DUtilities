package us.lynuxcraft.deadsilenceiv.dutilities;

import lombok.Getter;

public class Range {
    @Getter private double bottom;
    @Getter private double top;
    public Range(double bottom, double high){
        this.bottom = bottom;
        this.top = high;
    }

    public boolean contains(Double number){
        return (number >= bottom && number <= top);
    }
}
