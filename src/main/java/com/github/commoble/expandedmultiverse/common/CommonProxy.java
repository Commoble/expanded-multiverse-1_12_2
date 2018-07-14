package com.github.commoble.expandedmultiverse.common;

import com.github.commoble.expandedmultiverse.common.capability.portal_loader.IPortalLoaderCapability;
import com.github.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderFactory;
import com.github.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderStorage;
import com.github.commoble.expandedmultiverse.common.multiverse.DimensionLedger;
import com.github.commoble.expandedmultiverse.common.tileentity.TileEntityLedger;
import com.github.commoble.expandedmultiverse.common.world.WorldGenManager;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	// config values

	//

	
	public static WorldGenManager worldGenManager = new WorldGenManager();
	
	public static int modEntityID = 0;
	
	/**
	 * Run before anything else;
	 * Declare configuration parameters;
	 * Register everything that doesn't require something else to be registered first
	 */
	public void preInit(FMLPreInitializationEvent event)
	{
		
		// Registration
		// Blocks, Enchantments, Items, Potions, SoundEvents, and Biomes should be registered with registry events
		// Entities, Tile Entities, and Dimensions need to be registered here
		TileEntityLedger.registerTileEntities();
		//this.registerEntities();
		DimensionLedger.registerPlanes();
		GameRegistry.registerWorldGenerator(worldGenManager, 0);
		
		// register capabilities
		CapabilityManager.INSTANCE.register(IPortalLoaderCapability.class, new PortalLoaderStorage(), new PortalLoaderFactory());
	}
	
	/**
	 * The most important things to do in the main load event are:
	 * Register recipes, send FMLInterModComms messages to other mods, build data structures that shouldn't be in the other events
	 */
	public void load(FMLInitializationEvent event)
	{
		// register recipes
	}
	
	/**
	 * Handle interactions with other mods and complete setup
	 * e.g. registering creature spawning should go here due to other mods potentially creating new biomes
	 */
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	// empty particle methods
	
	public void spawnWormholeCoreParticles(World world, BlockPos pos)
	{
		// NOP, do stuff in client proxy
	}
}
