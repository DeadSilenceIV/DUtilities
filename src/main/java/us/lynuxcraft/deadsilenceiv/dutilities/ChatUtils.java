package us.lynuxcraft.deadsilenceiv.dutilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {

    public static void sendMessage(CommandSender sender,String message){
        boolean containsSplit = false;
        if(message.contains("\\n")){
            message = message.replaceAll("\\n","\n");
            containsSplit = true;
        }
        if(containsSplit || message.contains("\n")) {
            String[] split = message.split("\n");
            for (String line : split) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',line));
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
        }
    }

}
