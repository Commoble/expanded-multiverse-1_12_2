package com.commoble.expandedmultiverse.common.multiverse;

import java.util.HashMap;

import com.commoble.expandedmultiverse.common.ConfigMultiverse;
import com.commoble.expandedmultiverse.common.world.WorldGenManager;
import com.commoble.expandedmultiverse.common.world.WorldProviderPerpendicular;

import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

// class for holding dimension-related stuff
public class DimensionLedger
{
	private static DimensionWrapper[] dim_wrappers;
	private static HashMap<Integer, DimensionWrapper> idToWrapperMap;
	
	public static void registerPlanes()
	{
		// this will check for the data glob on the global world file
		// and initialize the data if it doesn't exist
		// this will permanently set the data on newly created worlds
		int max_dims = ConfigMultiverse.perpendicular_universe_count;
		System.out.println("Registering Expanded Multiverse Dimensions");
		DimensionLedger.dim_wrappers = new DimensionWrapper[max_dims];
		DimensionLedger.idToWrapperMap = new HashMap<Integer, DimensionWrapper>(max_dims);
		// register this many worlds
		for (int i=0; i<max_dims; i++)
		{
			int id = DimensionManager.getNextFreeDimId();
			String name = String.format("perp_%d", id);
			DimensionType dimtype = DimensionType.register(name, "_".concat(name), id, WorldProviderPerpendicular.class, false);
			dim_wrappers[i] = new DimensionWrapper(id, dimtype);
			DimensionManager.registerDimension(id, dimtype);
			idToWrapperMap.put(Integer.valueOf(id), dim_wrappers[i]);
		}
	}
	
	/**
	 * Returns the id for the universe with the given index,
	 * id being in the range [0, MAX) where MAX = the maximum number of universes
	 */
	public static int getPerpendicularUniverseID(int index)
	{
		return DimensionLedger.dim_wrappers[index].getID();
	}

	
	/**
	 * Returns the DimensionType for the universe with the given index,
	 * index being in the range [0, MAX) where MAX = the maximum number of universes
	 */
	public static DimensionType getPerpendicularUniverseType(int index)
	{
		return DimensionLedger.dim_wrappers[index].getDimType();
	}
	
	/**
	 * Returns the DimensionType for the given dimension ID.
	 * @param id A dimensionID, i.e. the numbers doled out by DimensionManager.getNextFreeId()
	 * @return The DimensionType corresponding to that ID
	 */
	public static DimensionType getPerpTypeFromID(int id)
	{
		return DimensionLedger.idToWrapperMap.get(Integer.valueOf(id)).getDimType();
	}
}
