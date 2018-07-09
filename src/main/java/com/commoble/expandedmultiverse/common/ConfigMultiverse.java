package com.commoble.expandedmultiverse.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;
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
	public void onConfigChangedEvent(OnConfigChangedEvent event)
	{
		if (event.getModID().equals(ExpandedMultiverseMod.MODID))
		{
			ConfigManager.sync(ExpandedMultiverseMod.MODID, Type.INSTANCE);
		}
	}
	
	// TODO consider letting min be 0 and handle appropriately
	@RangeInt(min=1, max=1000)
	@Comment("The number of different worlds that can be accessed via natural wormholes. If you change this and then play on a world that used the old number, many things will break, so don't do that.")
	public static int perpendicular_universe_count = 97; // largish prime number

	@Comment("The first ID for perpendicular universe dimensions. The IDs used will form a continuous block of the size of perpendicular_universe_count.")
	public static int perpendicular_universe_first_ID = 7171117;
	
}
