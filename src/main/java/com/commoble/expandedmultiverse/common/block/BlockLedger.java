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
	// name strings
	public static final String wormholeCore_name = "wormholecore";
	
	// block fields
	public static BlockWormholeCore blockWormholeCore;
	
	// register all the blocks, called by RegistryEventHandler
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		//grinderBlock = (BlockGrinder)registerBlock(event.getRegistry(), new BlockGrinder(), "grinder");
		BlockLedger.blockWormholeCore = registerBlock(registry, new BlockWormholeCore(), wormholeCore_name);
	}
	
	private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
	{
		name = ExpandedMultiverseMod.appendPrefix(name);
		newBlock.setUnlocalizedName(name);
		newBlock.setRegistryName(name);
		registry.register(newBlock);
		return newBlock;
	}
}
