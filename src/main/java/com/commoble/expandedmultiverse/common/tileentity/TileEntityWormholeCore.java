package com.commoble.expandedmultiverse.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
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
	private static final String IS_ACTIVE_NBT_KEY = "isActive";
	
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
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		// TODO Auto-generated method stub
		this.isActive = compound.getBoolean(TileEntityWormholeCore.IS_ACTIVE_NBT_KEY);
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		// TODO Auto-generated method stub
		compound.setBoolean(TileEntityWormholeCore.IS_ACTIVE_NBT_KEY, this.isActive);
		return super.writeToNBT(compound);
	}
}
