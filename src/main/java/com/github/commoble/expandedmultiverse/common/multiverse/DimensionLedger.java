package com.github.commoble.expandedmultiverse.common.multiverse;

import com.github.commoble.expandedmultiverse.common.ConfigMultiverse;
import com.github.commoble.expandedmultiverse.common.world.WorldProviderPerpendicular;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.item.Item;
import net.minecraft.util.ReportedException;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

// class for holding dimension-related stuff
public class DimensionLedger
{
	//private static DimensionWrapper[] dim_wrappers;
	private static final Int2ObjectMap<DimensionWrapper> idToWrapperMap = new Int2ObjectOpenHashMap<>();
	private static int[] indexedPerpendicularIDs;	// zero-indexed array of perpendicular universe IDs
	public static DimensionType perpendicularDimensionType;
	
	public static void registerPlanes()
	{
		// this will check for the data glob on the global world file
		// and initialize the data if it doesn't exist
		// this will permanently set the data on newly created worlds
		int max_natural_dims = ConfigMultiverse.natural_universe_count;
		int first_id = ConfigMultiverse.natural_universe_first_ID;
		int max_total_dims = ConfigMultiverse.total_universe_count;
		
		assert max_total_dims >= max_natural_dims : "Total universe count cannot be lower than natural universe count, fix your settings";
		
		DimensionLedger.indexedPerpendicularIDs = new int[max_natural_dims];
		System.out.println("Registering Expanded Multiverse Dimensions");
		//DimensionLedger.dim_wrappers = new DimensionWrapper[max_dims];
		DimensionLedger.perpendicularDimensionType = DimensionType.register("perpendicular", "_perpendicular", 7, WorldProviderPerpendicular.class, false);
		// register this many worlds
		for (int i=0; i<max_total_dims; i++)
		{
			int id = first_id + i;
			DimensionManager.registerDimension(id, perpendicularDimensionType);
			DimensionLedger.indexedPerpendicularIDs[i] = id;
		}
	}
	
	/**
	 * Returns the id for the universe with the given index,
	 * id being in the range [0, MAX) where MAX = the maximum number of universes
	 */
	public static int getPerpendicularUniverseID(int index)
	{
		return DimensionLedger.indexedPerpendicularIDs[index];
	}

	
	/**
	 * Returns the DimensionType for the universe with the given index,
	 * index being in the range [0, MAX) where MAX = the maximum number of universes
	 */
//	public static DimensionType getPerpendicularUniverseType(int index)
//	{
//		return DimensionLedger.dim_wrappers[index].getDimType();
//	}
//	
	/**
	 * Returns the DimensionType for the given dimension ID.
	 * @param id A dimensionID, i.e. the numbers doled out by DimensionManager.getNextFreeId()
	 * @return The DimensionType corresponding to that ID
	 */
//	public static DimensionType getPerpTypeFromID(int id)
//	{
//		return DimensionLedger.idToWrapperMap.get(Integer.valueOf(id)).getDimType();
//	}
}
