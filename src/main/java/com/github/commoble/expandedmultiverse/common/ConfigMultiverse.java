package com.github.commoble.expandedmultiverse.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@Config(modid=ExpandedMultiverseMod.MODID, type=Type.INSTANCE, category="multiverse")
public class ConfigMultiverse
{
	@SubscribeEvent
	public static void onConfigChangedEvent(OnConfigChangedEvent event)
	{
		if (ExpandedMultiverseMod.MODID.equals(event.getModID()))
		{
			ConfigManager.sync(ExpandedMultiverseMod.MODID, Type.INSTANCE);
		}
	}
	
	// TODO consider letting min be 0 and handle appropriately
	@RangeInt(min=1, max=1000)
	@RequiresMcRestart
	@Comment("The number of different worlds that can be accessed via natural wormholes. Client and server must have the same config value. Changing this will break existing worlds.")
	public static int natural_universe_count = 19; // largish prime number

	@RequiresMcRestart
	@Comment("The first ID for natural universe dimensions. The IDs used will form a continuous block. Client and server must have the same config value. Changing this will break existing worlds.")
	public static int natural_universe_first_ID = 7171117;
	
	@RangeInt(min=1, max=1000)
	@RequiresMcRestart
	@Comment("The total number of different worlds that can be accessed from natural or tuned wormholes. Client and server must have the same config value. Changing this will break existing worlds. Should not be lower than the natural universe count.")
	public static int total_universe_count = 100;
}
