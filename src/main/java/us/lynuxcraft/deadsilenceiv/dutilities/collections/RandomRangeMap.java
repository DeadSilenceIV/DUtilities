package us.lynuxcraft.deadsilenceiv.dutilities.collections;

import lombok.Getter;
import lombok.Setter;
import us.lynuxcraft.deadsilenceiv.dutilities.RandomUtils;
import us.lynuxcraft.deadsilenceiv.dutilities.Range;
import us.lynuxcraft.deadsilenceiv.dutilities.Rateable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomRangeMap<K extends Rateable>{
    @Getter private Map<K,Range> map;
    private Double maxChance;
    @Getter @Setter private Integer randomGenerationLimit;
    @Getter @Setter private int randomGenerationCount;
    public RandomRangeMap() {
        map = new HashMap<>();
        randomGenerationCount = 0;
    }

    public RandomRangeMap(List<K> content){
        this();
        populate(content);
    }

    public void populate(List<K> content){
        if(!content.isEmpty()) {
            // Invalidates the cache
            maxChance = null;
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
        return getRandom(true);
    }

    public K getRandom(boolean increaseGenerationCounter){
        if(!map.isEmpty() && (randomGenerationLimit == null || randomGenerationCount < randomGenerationLimit)) {
            double number = RandomUtils.getRandomDoubleBetween(0, getMaxChance());
            for (K object : map.keySet()) {
                Range range = map.get(object);
                if (range.contains(number)) {
                    if(increaseGenerationCounter) {
                        randomGenerationCount++;
                    }
                    return object;
                }
            }
        }
        return null;
    }

    public Double getMaxChance(){
        if(maxChance == null && !map.isEmpty()) {
            maxChance = 0.0;
            for (K object : map.keySet()) {
                Range range = map.get(object);
                if (range.getTop() > maxChance) {
                    maxChance = range.getTop();
                }
            }
        }
        return maxChance;
    }

}
