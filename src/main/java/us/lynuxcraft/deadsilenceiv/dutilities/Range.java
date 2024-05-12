package us.lynuxcraft.deadsilenceiv.dutilities;


public class Range extends BaseInterval<Double>{
    public Range(double bottom, double top){
        super(bottom,top,false,false);
    }

    @Override
    public boolean contains(Double number){
        boolean first = (bottomOpened) ? number > bottom : number >= bottom;
        boolean second = (topOpened) ? number > top : number >= top;
        return (first && second);
    }
}
