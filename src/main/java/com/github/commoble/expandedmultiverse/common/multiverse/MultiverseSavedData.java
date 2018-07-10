package com.github.commoble.expandedmultiverse.common.multiverse;

import com.github.commoble.expandedmultiverse.common.ConfigMultiverse;
import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

/**
 * WorldSavedData attached to the global save file
 * @author Joseph
 *
 */
public class MultiverseSavedData extends WorldSavedData
{
	// name of this WorldSavedData in the global save file
	private static final String DATA_NAME = ExpandedMultiverseMod.MODID + "_data";
	//private static final String PERP_UNIVERSE_COUNT_STRING = ExpandedMultiverseMod.MODID + "_perpendicular_universe_count";
	//private int perpendicular_universe_count;
	
	public MultiverseSavedData()
	{
		super(DATA_NAME);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		//this.perpendicular_universe_count = nbt.getInteger(PERP_UNIVERSE_COUNT_STRING);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		// TODO Auto-generated method stub
		//compound.setInteger(PERP_UNIVERSE_COUNT_STRING, this.perpendicular_universe_count);
		return compound;
	}
	
//	public int getPerpendicularUniverseCount()
//	{
//		return this.perpendicular_universe_count;
//	}
	
	// gets the data from the global storage, initializing it if necessary
	public static MultiverseSavedData get(World world)
	{
		MapStorage storage = world.getMapStorage(); // global data
		MultiverseSavedData instance = (MultiverseSavedData)storage.getOrLoadData(MultiverseSavedData.class, DATA_NAME);
		
//		if (instance == null)	// nothing in the data yet
//		{
//			instance = new MultiverseSavedData();
//			instance.perpendicular_universe_count = DimensionLedger.MAX_DIMS;//ConfigMultiverse.perpendicular_universe_count;
//			storage.setData(DATA_NAME, instance);
//		}
		
		return instance;
	}
}
