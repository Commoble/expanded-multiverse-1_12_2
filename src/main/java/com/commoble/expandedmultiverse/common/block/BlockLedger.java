package com.commoble.expandedmultiverse.common.block;

import com.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering blocks and storing their references
 */
public class BlockLedger
{	
	// register all the blocks, called by RegistryEventHandler
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		//grinderBlock = (BlockGrinder)registerBlock(event.getRegistry(), new BlockGrinder(), "grinder");
	}
	
	private static Block registerBlock(IForgeRegistry<Block> registry, Block newBlock, String name)
	{
		name = ExpandedMultiverseMod.appendPrefix(name);
		newBlock.setUnlocalizedName(name);
		newBlock.setRegistryName(name);
		registry.register(newBlock);
		return newBlock;
	}
}
