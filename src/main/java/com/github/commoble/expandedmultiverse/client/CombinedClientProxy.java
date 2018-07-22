package com.github.commoble.expandedmultiverse.client;

import com.github.commoble.expandedmultiverse.client.particle.ParticleWormholeCore;
import com.github.commoble.expandedmultiverse.client.particle.ParticleWormholeSwirlyBit;
import com.github.commoble.expandedmultiverse.client.render.tesr.TESRWormholeCore;
import com.github.commoble.expandedmultiverse.common.CommonProxy;
import com.github.commoble.expandedmultiverse.common.tileentity.TileEntityWormholeCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
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
		double xbase = pos.getX();
		double ybase = pos.getY();
		double zbase = pos.getZ();
		double x = xbase + 0.25D + (world.rand.nextDouble()*0.5D);
		double y = ybase + 0.75D + (world.rand.nextDouble()*0.1D - 0.05D);
		double z = zbase + 0.25D + (world.rand.nextDouble()*0.5D);
		ParticleWormholeCore part = new ParticleWormholeCore(world, x, y, z);
		ParticleManager particleManager = Minecraft.getMinecraft().effectRenderer;
		particleManager.addEffect(part);
		x = xbase - 1.5D + (world.rand.nextDouble()*4D); // = 0.5 + ([0,4) - 2), diameter 4, radius 2, centered on x+0.5F
		y = ybase - 1.5D + (world.rand.nextDouble()*4D);
		z = zbase - 1.5D + (world.rand.nextDouble()*4D);
		ParticleWormholeSwirlyBit swirlypart = new ParticleWormholeSwirlyBit(
				world, x, y, z,
				xbase+0.5, ybase+0.5, zbase+0.5);
		particleManager.addEffect(swirlypart);
	}
}
