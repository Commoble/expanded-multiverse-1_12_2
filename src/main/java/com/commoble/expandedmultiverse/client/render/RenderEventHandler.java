package com.commoble.expandedmultiverse.client.render;

import java.util.LinkedList;
import java.util.Random;

import com.commoble.expandedmultiverse.client.render.overlay.SpyglassOverlayRenderer;
import com.commoble.expandedmultiverse.common.capability.portal_loader.IPortalLoaderCapability;
import com.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderCapability;
import com.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderProvider;
import com.commoble.expandedmultiverse.common.item.ItemLedger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RenderEventHandler
{
	//private SpyglassOverlayRenderer spyglassOverlayRenderer = new SpyglassOverlayRenderer();
	
	@SubscribeEvent
	public static void onPreRender(RenderGameOverlayEvent.Pre e)
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.player;
		if (player == null)
			return;	// just in case
		
		if (e.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE)
		{
			if (player.getHeldItemMainhand().getItem() == ItemLedger.spyglassItem)
			{
				SpyglassOverlayRenderer.render(mc, e.getResolution().getScaledWidth(), e.getResolution().getScaledHeight());
			}
			
			IPortalLoaderCapability cap = player.getCapability(PortalLoaderProvider.PORTAL_LOADER_CAP, null);
			
			if (cap.getTicksInPortal() > 0)
			{
				RenderEventHandler.renderWormholeOverlay(mc, player, cap, e.getResolution().getScaledWidth(), e.getResolution().getScaledHeight());
			}
		}
	}
	
	public static void renderWormholeOverlay(Minecraft minecraft, EntityPlayerSP player, IPortalLoaderCapability cap, int screenWidth, int screenHeight)
	{
		// do the overlay
		int ticks = cap.getTicksInPortal();
		float multiplier = (float)ticks / (float)PortalLoaderCapability.TICKS_TO_INITIATE_TELEPORT;
		float red = 0.1F * multiplier + 0.8F;
		float green = 0.8F * multiplier + 0.1F;
		float blue = 0.1F * multiplier + 0.8F;
		float alpha = 0.8F * multiplier;
		RenderBuddy.drawRect(0, 0, screenWidth, screenHeight, red, green, blue, alpha);
		
		// then spawn some funky particles everywhere
		World world = player.world;
		Random rand = world.rand;
		LinkedList<Vec3d> particleSpawnList = new LinkedList<Vec3d>();
		
		// the exact player position here isn't important
		double xBase = player.posX;
		double yBase = player.posY;
		double zBase = player.posZ;
		
		for (int i = 0; i<ticks*2; i++)
		{
			double xOff = rand.nextDouble()*10D - 5D;	// -20 to 20ish
			double yOff = rand.nextDouble()*10D - 5D;
			double zOff = rand.nextDouble()*10D - 5D;
			
			particleSpawnList.add(new Vec3d(xBase + xOff, yBase + yOff, zBase + zOff));
		}
		
		for (Vec3d vec : particleSpawnList)
		{
			world.spawnParticle(EnumParticleTypes.END_ROD, vec.x, vec.y, vec.z, 0D, ((double)ticks)/5000D, 0D);
		}
	}
}
