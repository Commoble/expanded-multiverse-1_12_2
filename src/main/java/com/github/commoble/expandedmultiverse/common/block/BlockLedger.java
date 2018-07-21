package com.github.commoble.expandedmultiverse.common.block;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering blocks and storing their references
 */
public class BlockLedger
{	
	// name strings
	public static final String WORMHOLE_CORE_NAME = "wormholecore";
	public static final String STABILIZER_NAME = "stabilizer";
	
	// block fields
	public static BlockWormholeCore blockWormholeCore;
	public static BlockStabilizer blockStabilizer;
	
	// register all the blocks, called by RegistryEventHandler
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		//grinderBlock = (BlockGrinder)registerBlock(event.getRegistry(), new BlockGrinder(), "grinder");
		BlockLedger.blockWormholeCore = registerBlock(registry, new BlockWormholeCore(), WORMHOLE_CORE_NAME);
		BlockLedger.blockStabilizer = registerBlock(registry, new BlockStabilizer(), STABILIZER_NAME);
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
