package us.lynuxcraft.deadsilenceiv.dutilities.collections;

import lombok.Getter;
import us.lynuxcraft.deadsilenceiv.dutilities.RandomUtils;
import us.lynuxcraft.deadsilenceiv.dutilities.Range;
import us.lynuxcraft.deadsilenceiv.dutilities.Rateable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomRangeMap<K extends Rateable>{
    @Getter private Map<K,Range> map;
    public RandomRangeMap() {
        map = new HashMap<>();
    }

    public RandomRangeMap(List<K> content){
        map = new HashMap<>();
        populate(content);
    }

    public void populate(List<K> content){
        if(!content.isEmpty()) {
            for(int i = 0; i <= content.size() - 1; i++) {
                K object = content.get(i);
                K lastObject = (i-1 >= 0) ? content.get(i-1) : null;
                Range range;
                if(lastObject != null){
                    Range lastRange = map.get(lastObject);
                    range = new Range(lastRange.getTop(),lastRange.getTop()+object.getChance());
                }else{
                    range = new Range(0.0,object.getChance());
                }
                map.put(object,range);
            }
        }
    }

    public K getRandom(){
        if(!map.isEmpty()) {
            double number = RandomUtils.getRandomDoubleBetween(0, getMaxChance());
            for (K object : map.keySet()) {
                Range range = map.get(object);
                if (range.contains(number)) {
                    return object;
                }
            }
        }
        return null;
    }

    public double getMaxChance(){
        double max = 0.0;
        if(!map.isEmpty()) {
            for (K object : map.keySet()) {
                Range range = map.get(object);
                if (range.getTop() > max) {
                    max = range.getTop();
                }
            }
        }
        return max;
    }

}
