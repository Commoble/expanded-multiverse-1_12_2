package com.github.commoble.expandedmultiverse.common;

import com.github.commoble.expandedmultiverse.common.capability.portal_loader.IPortalLoaderCapability;
import com.github.commoble.expandedmultiverse.common.capability.portal_loader.PortalLoaderProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LivingEventHandler
{
	// this fires on both client and server
	@SubscribeEvent
	public static void onLivingUpdateEvent(LivingUpdateEvent e)
	{
		if (e.getEntity() instanceof EntityPlayer)
		{
			// handle portal timer stuff
			
			EntityPlayer ent = (EntityPlayer) e.getEntity();
			
			IPortalLoaderCapability cap = ent.getCapability(PortalLoaderProvider.PORTAL_LOADER_CAP, null);
			int cooldown = cap.getCooldownTicks();
			if (cooldown > 0)
			{
				cap.addCooldownTicks(-1);
			}
			else if (cooldown < 0)
			{
				cap.addCooldownTicks(1);
			}
			else // cooldown done
			{
				int ticksInPortal = cap.getTicksInPortal();
				if (ticksInPortal > 0)
				{
					cap.addTicksInPortal(-1);
				}
			}
		}
	}
}
