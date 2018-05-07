package com.commoble.expandedmultiverse.common.world;

import com.commoble.expandedmultiverse.common.multiverse.DimensionLedger;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderGeneric extends WorldProvider
{
	protected long subseed;	// use this seed instead of server seed where possible
	protected WorldInfo modifiedWorldInfo;	// set of base worldinfo with the seed changed
	
	// called during construction after world and worldtype are set
	@Override
	protected void init()
	{	// add the square of the dimID to the server seed to get the de facto seed for this particular world
		super.init();
		this.subseed = super.getSeed() + (((long)this.getDimension()) * ((long)this.getDimension()));
		this.modifiedWorldInfo = this.getModifiedWorldInfo(this.world.getWorldInfo(), this.subseed);
		this.biomeProvider = new BiomeProviderPerpendicular(this.modifiedWorldInfo);
	}
	
	// using the WorldInfo for the primary surface world,
	// return a mostly identical WorldInfo except with a seed modified for this WorldProvider
	public WorldInfo getModifiedWorldInfo(WorldInfo baseWorldInfo, long seed)
	{
		WorldInfo newWorldInfo = new WorldInfo(baseWorldInfo);
		newWorldInfo.populateFromWorldSettings(
				new WorldSettings(seed,
						baseWorldInfo.getGameType(),
						baseWorldInfo.isMapFeaturesEnabled(),
						baseWorldInfo.isHardcoreModeEnabled(),
						baseWorldInfo.getTerrainType())
				.setGeneratorOptions(baseWorldInfo.getGeneratorOptions()));
		newWorldInfo.setWorldTime(baseWorldInfo.getWorldTime());
		newWorldInfo.setWorldTotalTime(baseWorldInfo.getWorldTotalTime());
		return newWorldInfo;
	}

	@Override
	public DimensionType getDimensionType()
	{
		// TODO Auto-generated method stub
		return DimensionLedger.getPerpTypeFromID(this.getDimension());
	}

    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorPerpendicular(this.world, this.subseed, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getWorldInfo().getGeneratorOptions());
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

    @Override
    public long getSeed()
    {	
		return this.subseed;
    }
}
