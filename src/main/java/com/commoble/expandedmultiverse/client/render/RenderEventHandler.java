package com.commoble.expandedmultiverse.client.render;

import com.commoble.expandedmultiverse.client.render.overlay.SpyglassOverlayRenderer;
import com.commoble.expandedmultiverse.common.capability.portal_loader.IPortalLoaderCapability;
import com.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderCapability;
import com.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderProvider;
import com.commoble.expandedmultiverse.common.item.ItemLedger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
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
				RenderEventHandler.renderWormholeOverlay(mc, cap, e.getResolution().getScaledWidth(), e.getResolution().getScaledHeight());
			}
		}
	}
	
	public static void renderWormholeOverlay(Minecraft minecraft, IPortalLoaderCapability cap, int screenWidth, int screenHeight)
	{
		float multiplier = (float)cap.getTicksInPortal() / (float)PortalLoaderCapability.TICKS_TO_INITIATE_TELEPORT;
		float red = 0.9F * multiplier;
		float green = 0.4F * multiplier + 0.1F;
		float blue = 0.8F * multiplier;
		float alpha = 0.9F * multiplier;
		RenderBuddy.drawRect(0, 0, screenWidth, screenHeight, red, green, blue, alpha);
	}
}
