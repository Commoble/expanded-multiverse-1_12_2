package com.commoble.expandedmultiverse.common;

import com.commoble.expandedmultiverse.common.multiverse.DimensionLedger;
import com.commoble.expandedmultiverse.common.tileentity.TileEntityLedger;
import com.commoble.expandedmultiverse.common.world.WorldGenManager;
import com.commoble.expandedmultiverse.common.world.WorldProviderGeneric;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		Property conf;
		config.load();
		
		// TODO setup config file here
		
		config.save();
		
		// Registration
		// Blocks, Enchantments, Items, Potions, SoundEvents, and Biomes should be registered with registry events
		// Entities, Tile Entities, and Dimensions need to be registered here
		TileEntityLedger.registerTileEntities();
		//this.registerEntities();
		this.registerPlanes();
		GameRegistry.registerWorldGenerator(worldGenManager, 0);
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
	
	private void registerPlanes()
	{
		DimensionLedger.test_dim_id = DimensionManager.getNextFreeDimId();
		DimensionLedger.test_dim_type = DimensionType.register("generic_universe", "_generic_universe", DimensionLedger.test_dim_id, WorldProviderGeneric.class, false);
		DimensionManager.registerDimension(DimensionLedger.test_dim_id,  DimensionLedger.test_dim_type);
	}
}
