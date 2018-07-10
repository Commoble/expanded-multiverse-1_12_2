package com.commoble.expandedmultiverse.common.capability;

import com.commoble.expandedmultiverse.common.ExpandedMultiverseMod;
import com.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CapabilityEventHandler
{
	public static final ResourceLocation portalLoaderID = new ResourceLocation(ExpandedMultiverseMod.MODID, "portal_loader");
	@SubscribeEvent
	public static void onAttachCapabilitiesToEntities(AttachCapabilitiesEvent<Entity> e)
	{
		Entity ent = e.getObject();
		if (ent instanceof EntityPlayer)
		{
			e.addCapability(CapabilityEventHandler.portalLoaderID, new PortalLoaderProvider());
		}
	}
}
