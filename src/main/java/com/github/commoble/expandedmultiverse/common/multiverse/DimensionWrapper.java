package com.commoble.expandedmultiverse.common.multiverse;

import net.minecraft.world.DimensionType;

class DimensionWrapper
{
	private final int id;
	private final DimensionType dimtype;
	
	DimensionWrapper(int id, DimensionType dimtype)
	{
		this.id = id;
		this.dimtype = dimtype;
	}
	
	int getID()
	{
		return this.id;
	}
	
	DimensionType getDimType()
	{
		return this.dimtype;
	}
}