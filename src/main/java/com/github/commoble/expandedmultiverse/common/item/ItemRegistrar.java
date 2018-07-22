package com.github.commoble.expandedmultiverse.common.item;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;
import com.github.commoble.expandedmultiverse.common.block.BlockNames;
import com.github.commoble.expandedmultiverse.common.block.BlockRegistrar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering items and itemblocks, and keeping their references
 * also handle creative tabs since those are closely related to items
 */
@ObjectHolder(ExpandedMultiverseMod.MODID)
public class ItemRegistrar
{
	
	// itemblocks
	// field names here should match the registry names of the blocks
	public static final Item wormhole_core = null;
	public static final Item stabilizer = null;
	
	// true items
	public static final Item spyglass = null;
	
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registerItem(registry, new ItemBlock(BlockRegistrar.wormhole_core), BlockNames.WORMHOLE_CORE_NAME);
		registerItem(registry, new ItemBlock(BlockRegistrar.stabilizer), BlockNames.STABILIZER_NAME);
		
		registerItem(registry, new ItemSpyglass(), ItemNames.SPYGLASS_NAME);
	}
	
	private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name)
	{
		name = ExpandedMultiverseMod.appendPrefix(name);
		newItem.setUnlocalizedName(name);
		newItem.setRegistryName(name);
		registry.register(newItem);
		return newItem;
	}
}
