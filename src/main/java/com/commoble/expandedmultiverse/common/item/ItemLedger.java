package com.commoble.expandedmultiverse.common.item;

import com.commoble.expandedmultiverse.common.ExpandedMultiverseMod;
import com.commoble.expandedmultiverse.common.block.BlockLedger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering items and itemblocks, and keeping their references
 * also handle creative tabs since those are closely related to items
 */
public class ItemLedger
{
	// creative tab for the stuff
	public static final CreativeTabs emtab = new CreativeTabs("expandedmultiverse") {
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Items.ENDER_EYE);
		}
	};
	
	public static ItemBlock wormholeCoreItemBlock;
	public static ItemSpyglass spyglassItem;
	
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		
		wormholeCoreItemBlock = registerItemBlock(registry, new ItemBlock(BlockLedger.blockWormholeCore), "wormholecore");
		//grinderItemBlock = registerItemBlock(event.getRegistry(), new ItemBlock(BlockLedger.grinderBlock), "grinder");
		//grinderItemBlock.setCreativeTab(trtab);
		spyglassItem = registerItem(registry, new ItemSpyglass(), "spyglass");
	}
	
	private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name)
	{
		name = ExpandedMultiverseMod.appendPrefix(name);
		newItem.setUnlocalizedName(name);
		newItem.setRegistryName(name);
		registry.register(newItem);
		return newItem;
	}
	
	private static ItemBlock registerItemBlock(IForgeRegistry<Item> registry, ItemBlock newItemBlock, String name)
	{
		name = ExpandedMultiverseMod.appendPrefix(name);
		newItemBlock.setUnlocalizedName(name);
		newItemBlock.setRegistryName(name);
		registry.register(newItemBlock);
		return newItemBlock;
	}
}
