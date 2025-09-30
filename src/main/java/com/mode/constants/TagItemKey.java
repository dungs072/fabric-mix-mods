package com.mode.constants;

import com.mode.MainMode;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagItemKey {
    public static final TagKey<Item> REPAIRS_GUIDITE_ARMOR = TagKey.of(Registries.ITEM.getKey(),    
                                Identifier.of(MainMode.MOD_ID, "repairs_guidite_armor"));
}