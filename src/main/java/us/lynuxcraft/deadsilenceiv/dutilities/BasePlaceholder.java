package us.lynuxcraft.deadsilenceiv.dutilities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class BasePlaceholder implements Placeholder{
    @Getter
    private String sequence;
}
