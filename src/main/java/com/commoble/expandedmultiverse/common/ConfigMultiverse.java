package com.commoble.expandedmultiverse.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;

@Config(modid=ExpandedMultiverseMod.MODID, category="multiverse")
public class ConfigMultiverse
{
	// TODO consider letting min be 0 and handle appropriately
	@Name("Perpendicular Universe count")
	@RangeInt(min=1, max=1000)
	@RequiresWorldRestart
	@Comment("The number of different worlds that can be accessed via natural wormholes")
	public static int perpendicularUniverseCount = 32;
}
