package com.github.commoble.expandedmultiverse.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityWormholeCore extends TileEntity
{

	private boolean isActive;	// if true, wormhole is visible and acts as a portal when stood in
	public boolean getIsActive() {return this.isActive;}
	private static final String IS_ACTIVE_NBT_KEY = "isActive";
	
	public TileEntityWormholeCore()
	{
		this.isActive = false;
	}

	public void activateWormhole()
	{
		if (!this.getIsActive())
		{
			this.isActive = true;
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		this.isActive = compound.getBoolean(TileEntityWormholeCore.IS_ACTIVE_NBT_KEY);
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean(TileEntityWormholeCore.IS_ACTIVE_NBT_KEY, this.isActive);
		return super.writeToNBT(compound);
	}
	
	// called whenever chunkdata is sent to client
	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}
	
	// prepare a packet to sinc TE to client
	// this currently sends the entire NBT data in the packet
	// consider whittling the packet down if TE data becomes large
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}
	
	// get packet from server and read it into client TE
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.getNbtCompound());
	}
	
	
}
