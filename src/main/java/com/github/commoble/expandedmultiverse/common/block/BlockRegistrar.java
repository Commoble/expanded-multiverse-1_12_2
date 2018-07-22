package com.github.commoble.expandedmultiverse.common.block;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Class for registering blocks and storing their references
 */
@ObjectHolder(ExpandedMultiverseMod.MODID)
public class BlockRegistrar
{		
	// block fields
	public static final Block wormhole_core = null;
	public static final Block stabilizer = null;
	
	// register all the blocks, called by RegistryEventHandler
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		//grinderBlock = (BlockGrinder)registerBlock(event.getRegistry(), new BlockGrinder(), "grinder");
		registerBlock(registry, new BlockWormholeCore(), BlockNames.WORMHOLE_CORE_NAME);
		registerBlock(registry, new BlockStabilizer(), BlockNames.STABILIZER_NAME);
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
