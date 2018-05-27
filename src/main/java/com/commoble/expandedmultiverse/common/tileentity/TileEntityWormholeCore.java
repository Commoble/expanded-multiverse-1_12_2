package com.commoble.expandedmultiverse.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TileEntityWormholeCore extends TileEntity
{
	private boolean isActive;	// if true, wormhole is visible and acts as a portal when stood in
	public boolean getIsActive() {return this.isActive;}
	
	public TileEntityWormholeCore()
	{
		this.isActive = false;
	}

	public void activateWormhole(World world, BlockPos pos)
	{
		if (!this.getIsActive())
		{
			this.isActive = true;
		}
	}
}
