package com.mode.items;

import java.util.function.Function;

import com.mode.MainMode;
import com.mode.constants.GunKey;
import com.mode.constants.TagItemKey;
import com.mode.items.guns.BaseGun;
import com.mode.items.guns.settings.GunSettings;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item PISTOL = register(
		GunKey.PISTOL,
		settings -> new BaseGun((GunSettings) settings),
		new GunSettings().setDamage(7).setReloadTime(20)
	);
	
	public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(
		BlockTags.INCORRECT_FOR_WOODEN_TOOL,
		455,
		5.0F,
		1.5F,
		22,
		TagItemKey.REPAIRS_GUIDITE_ARMOR
	);
	
	public static void initialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
				.register((itemGroup) -> itemGroup.add(ModItems.PISTOL));
	}

	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MainMode.MOD_ID, name));
		Item item = itemFactory.apply(settings.registryKey(itemKey));
		Registry.register(Registries.ITEM, itemKey, item);

		return item;
	}

}