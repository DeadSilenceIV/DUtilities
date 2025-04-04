package us.lynuxcraft.deadsilenceiv.dutilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Queue;

public class AdventureUtils {

    public static Component replaceAll(String message,String word, Component replacement){
        return replaceAll(message,word,replacement,false);
    }

    public static Component replaceAll(String message,String word, Component replacement, boolean useAmpersand){
        Component newMessage = Component.empty();
        Queue<Range> ranges = StringFinder.find(message,word);
        char[] chars = message.toCharArray();
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];
            Range peek = ranges.peek();
            if(peek != null && i == peek.getBottom()){
                if(string.length() != 0){
                    if(!useAmpersand){
                        newMessage = newMessage.append(LegacyComponentSerializer.legacySection().deserialize(string.toString()));
                    }else{
                        newMessage = newMessage.append(LegacyComponentSerializer.legacyAmpersand().deserialize(string.toString()));
                    }
                    string = new StringBuilder();
                }
                newMessage = newMessage.append(replacement);
                ranges.poll();
                i += word.length()-1;
            }else{
                string.append(character);
            }
            if(i == chars.length-1 && string.length() != 0){
                if(!useAmpersand){
                    newMessage = newMessage.append(LegacyComponentSerializer.legacySection().deserialize(string.toString()));
                }else{
                    newMessage = newMessage.append(LegacyComponentSerializer.legacyAmpersand().deserialize(string.toString()));
                }
            }
        }
        return newMessage;
    }

}
