package us.lynuxcraft.deadsilenceiv.dutilities.inventory.items;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.lynuxcraft.deadsilenceiv.dutilities.Placeholder;
import us.lynuxcraft.deadsilenceiv.dutilities.inventory.Button;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ButtonItem<T extends Button<? extends InteractiveItem>> implements InteractiveItem {
    private static ExecutorService executor;
    @Getter protected final T button;
    @Getter protected final String name;
    @Getter protected final ItemStack itemStack;
    @Getter protected final Set<Placeholder> placeholders;
    @Getter protected ItemMeta originalMeta;
    private Optional<String> cachedOriginalName;
    private List<String> cachedOriginalLore;
    protected boolean initiallyRefreshed;

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
                name = name.replaceAll(placeholder, ChatColor.translateAlternateColorCodes('&', holder.getReplacement()));
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
                if (line.contains(placeholder)) {
                    line = line.replaceAll(placeholder, ChatColor.translateAlternateColorCodes('&', holder.getReplacement()));
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

    protected ItemMeta getUpdatedMeta() {
        ItemMeta updatedMeta = originalMeta.clone();
        updateName(updatedMeta);
        updateLore(updatedMeta);
        return updatedMeta;
    }

    protected static ExecutorService getExecutor(){
        if(executor == null){
            executor = Executors.newSingleThreadExecutor();
        }
        return executor;
    }

}
