package com.commoble.expandedmultiverse.common.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Contains a bunch of utility functions for editing the world
 * @author Joseph
 *
 */
public class WorldHelper
{
	/**
	 * Sets all the blocks in an area to be a specific blockstate
	 * min parameters must be less than or equal to max parameters
	 * Boundary parameters are inclusive! e.g. setting from -5 to 5 will include both positions
	 * @param xMin
	 * @param yMin
	 * @param zMin
	 * @param xMax
	 * @param yMax
	 * @param zMax
	 */
	public static void setBlocksInRect(IBlockState state, World world, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax)
	{
		if (yMin < 0)
		{
			yMin = 0;
		}
		if (yMax > world.getActualHeight() - 1)
		{
			yMax = world.getActualHeight() - 1;
		}
		for (BlockPos pos : BlockPos.getAllInBoxMutable(xMin, yMin, zMin, xMax, yMax, zMax))
		{
			world.setBlockState(pos, state);
		}
	}
}
