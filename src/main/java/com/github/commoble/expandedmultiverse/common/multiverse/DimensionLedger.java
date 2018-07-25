package com.github.commoble.expandedmultiverse.common.multiverse;

import com.github.commoble.expandedmultiverse.common.ConfigMultiverse;
import com.github.commoble.expandedmultiverse.common.world.WorldProviderUniverse;

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
	// some definitions:
	// Universe Prime: the overworld
	// 
	// perpendicular universe: a world that intersects Universe Prime along some higher-dimensional axis.
		// accessed from naturally occurring wormholes
		// superficially similar to Universe Prime due to the diffusion of matter and energy between universes
	// parallel universe: a world that does NOT intersect Universe Prime
		// must be accessed by using a Tesseract Impactor to punch a hole in the Firmament
		// can be much more unusual
	
	//private static DimensionWrapper[] dim_wrappers;
	//private static final Int2ObjectMap<DimensionWrapper> idToWrapperMap = new Int2ObjectOpenHashMap<>();
	private static int[] indexedUniverseIDs;	// zero-indexed array of dimension IDs for the perpendicular and parallel universes
	private static long[] indexedSeeds;	// the seeds for each universe
	public static DimensionType standardUniverseDimensionType;
	
	public static void registerPlanes()
	{
		// this will check for the data glob on the global world file
		// and initialize the data if it doesn't exist
		// this will permanently set the data on newly created worlds
		int max_natural_dims = ConfigMultiverse.natural_universe_count;
		int first_id = ConfigMultiverse.natural_universe_first_ID;
		int max_total_dims = ConfigMultiverse.total_universe_count;
		
		assert max_total_dims >= max_natural_dims : "Total universe count cannot be lower than natural universe count, fix your settings";
		
		DimensionLedger.indexedUniverseIDs = new int[max_natural_dims];
		System.out.println("Registering Expanded Multiverse Dimensions");
		//DimensionLedger.dim_wrappers = new DimensionWrapper[max_dims];
		DimensionLedger.standardUniverseDimensionType = DimensionType.register("universe", "_universe", 7, WorldProviderUniverse.class, false);
		// register this many worlds
		for (int i=0; i<max_total_dims; i++)
		{
			int id = first_id + i;
			DimensionManager.registerDimension(id, standardUniverseDimensionType);
			DimensionLedger.indexedUniverseIDs[i] = id;
		}
	}
	
	/**
	 * Returns the id for the universe with the given index,
	 * id being in the range [0, MAX) where MAX = the maximum number of universes
	 */
	public static int getPerpendicularUniverseID(int index)
	{
		return DimensionLedger.indexedUniverseIDs[index];
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
