package us.lynuxcraft.deadsilenceiv.dutilities.iridiumcolorapi.patterns;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;

public class ClassicPattern implements Pattern{

    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("#[a-fA-F0-9]{6}");

    @Override
    public String process(String message) {
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, "" + ChatColor.of(color));
            matcher = pattern.matcher(message);
        }

        return message;
    }

}
