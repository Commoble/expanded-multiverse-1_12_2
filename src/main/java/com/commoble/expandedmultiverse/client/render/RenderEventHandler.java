package com.commoble.expandedmultiverse.client.render;

import com.commoble.expandedmultiverse.client.render.overlay.SpyglassOverlayRenderer;
import com.commoble.expandedmultiverse.common.item.ItemLedger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		}
	}
}
