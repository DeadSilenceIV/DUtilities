package us.lynuxcraft.deadsilenceiv.dutilities.builders;

import com.cryptomorin.xseries.XMaterial;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class SkullBuilder extends ItemBuilder {
    public final static Map<String, SkullMeta> CACHED_META = new HashMap<>();
    private static ItemStack cachedSkull;
    private static Method setProfileMethod;
    private static Field profileField;
    public SkullBuilder(@NotNull String address) {
        stack = getSkull().clone();
        if(!CACHED_META.containsKey(address)){
            SkullMeta skullMeta = (SkullMeta)stack.getItemMeta();
            if(!setOwner(skullMeta,address) && address.length() > 16){
                skullMeta = setOwnerByUrl(stack, address);
            }else{
                stack.setItemMeta(skullMeta);
            }
            CACHED_META.put(address,skullMeta);
        }else{
            stack.setItemMeta(CACHED_META.get(address).clone());
        }
    }

    private boolean setOwner(SkullMeta skullMeta,String name){
        if(XMaterial.supports(20)){
            try {
                PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
                URL uniqueIdUrl = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                try (InputStreamReader uniqueIdReader = new InputStreamReader(uniqueIdUrl.openStream())){
                    String uuid = new JsonParser().parse(uniqueIdReader).getAsJsonObject().get("id").getAsString();
                    URL skinUrl = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
                    try(InputStreamReader skingReader = new InputStreamReader(skinUrl.openStream())){
                        JsonObject textureProperty = new JsonParser().parse(skingReader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
                        String address = textureProperty.get("value").getAsString();
                        String url = getUrlFromAddress(address);
                        PlayerTextures pt = profile.getTextures();
                        try {
                            pt.setSkin(new URL("http://textures.minecraft.net/texture/" + url));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        profile.setTextures(pt);
                        skullMeta.setOwnerProfile(profile);
                        return true;
                    }catch (Exception e){
                        return false;
                    }
                }catch (Exception e){
                    return false;
                }

            } catch (Exception e) {
                return false;
            }
        }
        return skullMeta.setOwner(name);
    }

    private SkullMeta setOwnerByUrl(ItemStack item, String address){
        SkullMeta headMeta = (SkullMeta) item.getItemMeta();
        if(XMaterial.supports(20)){
            PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
            PlayerTextures pt = profile.getTextures();
            String url = address;
            if(address.length() > 64){
                try {
                    url = getUrlFromAddress(address);
                }catch (IllegalArgumentException ignored){}
            }
            try {
                pt.setSkin(new URL("http://textures.minecraft.net/texture/" + url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            profile.setTextures(pt);
            headMeta.setOwnerProfile(profile);
            item.setItemMeta(headMeta);
            return headMeta;
        }
        return setOwnerByUrlLegacy(item,address,headMeta);
    }

    private String getUrlFromAddress(String address) throws IllegalArgumentException{
        // This the handlig for econded base 64 objects
        String json = new String(Base64.getDecoder().decode(address.getBytes()));
        String url = new JsonParser().parseString(json)
                .getAsJsonObject().get("textures")
                .getAsJsonObject().get("SKIN")
                .getAsJsonObject().get("url")
                .getAsString();
        if(url.contains("http://textures.minecraft.net/texture/")){
            url = url.replace("http://textures.minecraft.net/texture/","");
        }
        return url;
    }

    private SkullMeta setOwnerByUrlLegacy(ItemStack item,String url,SkullMeta headMeta){
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        byte[] data;
        if(url.length() <= 64) {
            data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"http://textures.minecraft.net/texture/%s\"}}}", url).getBytes());
        }else data = url.getBytes();
        profile.getProperties().put("textures", new Property("textures", new String(data)));
        if(XMaterial.supports(14)) {
            try {
                if(setProfileMethod == null) {
                    setProfileMethod = headMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                    setProfileMethod.setAccessible(true);
                }
                setProfileMethod.invoke(headMeta,profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                if(profileField == null) {
                    profileField = headMeta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                }
                profileField.set(headMeta, profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        item.setItemMeta(headMeta);
        return headMeta;
    }

    private static ItemStack getSkull(){
        if(cachedSkull == null){
            cachedSkull = XMaterial.PLAYER_HEAD.parseItem();
        }
        return cachedSkull;
    }

}
