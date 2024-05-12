package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.lynuxcraft.deadsilenceiv.dutilities.Placeholder;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.Button;

import java.util.*;


public class ButtonItem<T extends Button<? extends InteractiveItem>> implements InteractiveItem {
    @Getter protected final T button;
    @Getter protected final String name;
    @Getter protected final ItemStack itemStack;
    @Getter protected final Set<Placeholder> placeholders;
    @Getter protected ItemMeta originalMeta;
    private Optional<String> cachedOriginalName;
    private List<String> cachedOriginalLore;
    @Getter @Setter protected boolean initiallyRefreshed;
    public ButtonItem(T button, String name, ItemStack itemStack) {
        this.button = button;
        this.name = name;
        this.itemStack = itemStack;
        this.placeholders = new HashSet<>();
        this.originalMeta = itemStack.getItemMeta().clone();
        this.initiallyRefreshed = false;
    }

    public void setMeta(ItemMeta meta) {
        originalMeta = meta.clone();
        cachedOriginalName = null;
        cachedOriginalLore = null;
    }

    @Override
    public void addPlaceholder(Placeholder placeholder) {
        placeholders.add(placeholder);
    }

    protected void updateName(ItemMeta meta) {
        String originalName = getOriginalMetaName();
        if (originalName == null) return;
        String name = "" + originalName;
        if (name.isEmpty()) return;
        for (Placeholder holder : placeholders) {
            String placeholder = holder.getSequence();
            if (name.contains(placeholder)) {
                name = name.replace(placeholder, ChatColor.translateAlternateColorCodes('&', holder.getReplacement()));
            }
        }
        meta.setDisplayName(name);
    }

    protected void updateLore(ItemMeta meta) {
        List<String> originalLore = getOriginalMetaLore();
        if (originalLore == null) return;
        List<String> lore = new ArrayList<>(originalLore);
        if (lore.isEmpty()) return;
        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            for (Placeholder holder : placeholders) {
                String placeholder = holder.getSequence();
                String replacement;
                if (line.contains(placeholder) && (replacement = holder.getReplacement()) != null) {
                    line = line.replace(placeholder, ChatColor.translateAlternateColorCodes('&', replacement));
                }
            }
            if(line.contains("\\n")){
                line = line.replace("\\n","\n");
            }
            if(line.contains("\n")){
                String[] split = line.split("\n");
                int length = split.length;
                if(length > 1){
                    lore.addAll(i+1, Arrays.asList(split));
                    lore.remove(i);
                    i = i+length-1;
                    continue;
                }
            }
            lore.set(i, line);
        }
        meta.setLore(lore);
    }

    protected String getOriginalMetaName() {
        if (cachedOriginalName == null) {
            cachedOriginalName = Optional.ofNullable(originalMeta.getDisplayName());
        }
        return cachedOriginalName.orElse(null);
    }

    protected List<String> getOriginalMetaLore() {
        if (cachedOriginalLore == null) {
            cachedOriginalLore = originalMeta.getLore();
        }
        return cachedOriginalLore;
    }

    public ItemMeta getUpdatedMeta() {
        ItemMeta updatedMeta = originalMeta.clone();
        updateName(updatedMeta);
        updateLore(updatedMeta);
        return updatedMeta;
    }

}
