package com.github.commoble.expandedmultiverse.client;

import com.github.commoble.expandedmultiverse.client.particle.ParticleWormholeCore;
import com.github.commoble.expandedmultiverse.client.render.tesr.TESRWormholeCore;
import com.github.commoble.expandedmultiverse.common.CommonProxy;
import com.github.commoble.expandedmultiverse.common.tileentity.TileEntityWormholeCore;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
* CombinedClient is used to set up the mod and start it running when installed on a normal minecraft client.
* It should not contain any code necessary for proper operation on a DedicatedServer.
* Code required for both normal minecraft client and dedicated server should go into CommonProxy.
* 
* All client-side-specific things (rendering and textures, mostly) goes in here
*/
public class CombinedClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);	// run CommonProxy's preInit first to get things registered
		
		// entity renderers
		
		// tile entity renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWormholeCore.class, new TESRWormholeCore());
	}
	
	@Override
	public void load(FMLInitializationEvent event)
	{
		super.load(event);
		
		// register item renderers here -- the mesher hasn't been initialized yet in preinit
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
	
	@Override
	public void spawnWormholeCoreParticles(World world, BlockPos pos)
	{
		double x = pos.getX() + 0.25D + (world.rand.nextFloat()*0.5F);
		double y = pos.getY() + 0.75D + (world.rand.nextFloat()*0.1F - 0.05F);
		double z = pos.getZ() + 0.25D + (world.rand.nextFloat()*0.5F);
		ParticleWormholeCore part = new ParticleWormholeCore(world, x, y, z);
		Minecraft.getMinecraft().effectRenderer.addEffect(part);
	}
}
