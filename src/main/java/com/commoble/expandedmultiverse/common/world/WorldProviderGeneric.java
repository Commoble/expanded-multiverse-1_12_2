package com.commoble.expandedmultiverse.common.world;

import com.commoble.expandedmultiverse.common.multiverse.DimensionLedger;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class WorldProviderGeneric extends WorldProvider
{

	@Override
	public DimensionType getDimensionType()
	{
		// TODO Auto-generated method stub
		return DimensionLedger.test_dim_type;
	}

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

}
