package io.rqndomhax.uhcapi.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ItemBuilder {
    private ItemStack is;

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(ItemStack is) {
        this.is = is;
    }

    public ItemBuilder(Material m, int amount) {
        is = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, short meta){
        is = new ItemStack(m, amount, meta);
    }

    public ItemBuilder clone() {
        return new ItemBuilder(is);
    }

    public ItemBuilder setDurability(short dur) {
        is.setDurability((short) (is.getType().getMaxDurability() - dur));
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public String getName() {
        ItemMeta im = is.getItemMeta();
        if (im == null)
            return "";
        String name = "";
        name += im.getDisplayName();
        return name;
    }

    public ItemBuilder addStoredEnchant(Enchantment ench, int level) {
        if (is.getType() != Material.ENCHANTED_BOOK) return this;

        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        ((EnchantmentStorageMeta) im).addStoredEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeStoredEnchant(Enchantment ench) {
        if (is.getType() != Material.ENCHANTED_BOOK) return this;

        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        ((EnchantmentStorageMeta) im).removeStoredEnchant(ench);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        SkullMeta im = (SkullMeta) is.getItemMeta();
        if (im == null)
            return this;
        im.setOwner(owner);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setInfinityDurability() {
        is.setDurability(Short.MAX_VALUE);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            if (im == null)
                return this;
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public ItemBuilder hideEnchants(){
        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        ItemMeta im = is.getItemMeta();
        if (im == null)
            return this;
        im.addItemFlags(flag);
        is.setItemMeta(im);
        return this;
    }

    public ItemStack toItemStack() {
        return is;
    }
}
