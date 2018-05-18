package com.commoble.expandedmultiverse.common.world;

import java.util.Random;

import com.commoble.expandedmultiverse.common.multiverse.DimensionLedger;

import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class WorldProviderPerpendicular extends WorldProvider
{
	protected long subseed;	// use this seed instead of server seed where possible
	protected WorldInfo modifiedWorldInfo;	// set of base worldinfo with the seed changed
	protected int[] aspectData;
	
	public static final float MAX_MAX_LIGHT = 4F;
	public static final float BASE_MAX_LIGHT = 1F;
	public static final float MIN_MAX_LIGHT = 0.9F;
	public static final float MAX_MIN_LIGHT = 0.3F;
	public static final float BASE_MIN_LIGHT = 0F;
	public static final float MIN_MIN_LIGHT = -0.1F;
	
	// called during construction after world and worldtype are set
	@Override
	protected void init()
	{	// add the square of the dimID to the server seed to get the de facto seed for this particular world
		super.init();
		this.subseed = (super.getSeed() + (((long)this.getDimension()) * ((long)this.getDimension()))) * super.getSeed() + this.getDimension();
		this.modifiedWorldInfo = this.getModifiedWorldInfo(this.world.getWorldInfo(), this.subseed);
		this.biomeProvider = new BiomeProviderPerpendicular(this.modifiedWorldInfo);
		
		// setup aspect data
		this.aspectData = new int[EnumWorldAspect.COUNT];
		Random rand = new Random(this.subseed);
		for (int i=0; i<EnumWorldAspect.COUNT; i++)
		{
			// set aspect in range between -16 and 15
			this.aspectData[i] = -128 + rand.nextInt(256);
			//this.aspectData[i] = rand.nextInt(EnumWorldAspect.CEILING_FOR_AVERAGE * 2) - rand.nextInt(EnumWorldAspect.CEILING_FOR_AVERAGE);
		}
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
    
    public int getAspect(EnumWorldAspect aspect)
    {
    	return this.aspectData[aspect.ordinal()];
    }
    
    /**
     * Returns a number in the range [-1.0F, 1.0F) based on the given Aspect
     */
    public float getAspectFactor(EnumWorldAspect aspect)
    {
    	int aspectValue = this.getAspect(aspect);
    	return (float)aspectValue / (float)EnumWorldAspect.MAX;
    }

	@Override
	protected void generateLightBrightnessTable()
	{
		// lightning based on aspects
		float lightAspect = this.getAspect(EnumWorldAspect.CREATION);
		float darkAspect = this.getAspect(EnumWorldAspect.DESTRUCTION);
		
		float minLight;
		if (darkAspect < 0)
		{
			// negative * negative / negative = negative
			minLight = BASE_MIN_LIGHT + (MIN_MIN_LIGHT - BASE_MIN_LIGHT)* (darkAspect / EnumWorldAspect.MIN);
		}
		else
		{
			minLight = BASE_MIN_LIGHT + (MAX_MIN_LIGHT - BASE_MIN_LIGHT) * (darkAspect / EnumWorldAspect.MAX);
		}

		float maxLight;
		if (lightAspect < 0)
		{
			maxLight = BASE_MAX_LIGHT + (MIN_MAX_LIGHT - BASE_MAX_LIGHT) * (lightAspect / EnumWorldAspect.MIN);
		}
		else
		{
			maxLight = BASE_MAX_LIGHT + (MAX_MAX_LIGHT - BASE_MAX_LIGHT) * (lightAspect / EnumWorldAspect.MAX);
		}

        for (int i = 0; i <= 15; ++i)
        {
            float baseLight = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - baseLight) / (baseLight * 3.0F + 1.0F) * (maxLight - minLight) + minLight;
        }
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		// TODO Auto-generated method stub
		return super.calculateCelestialAngle(worldTime, partialTicks);
	}

	@Override
	public int getMoonPhase(long worldTime)
	{
		// TODO Auto-generated method stub
		return super.getMoonPhase(worldTime);
	}

	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
	{
		// TODO Auto-generated method stub
		return super.calcSunriseSunsetColors(celestialAngle, partialTicks);
	}

	@Override
	public Vec3d getFogColor(float angle, float partialTicks)
	{
		// TODO Auto-generated method stub
		Vec3d baseFog = super.getFogColor(angle, partialTicks);
		float redFactor = 0.5F * this.getAspectFactor(EnumWorldAspect.CREATION) + 0.5F;
		float greenFactor = 0.5F * this.getAspectFactor(EnumWorldAspect.LIFE) + 0.5F;
		float blueFactor = 0.5F * this.getAspectFactor(EnumWorldAspect.DEATH) + 0.5F;
		float cyanFactor = 0.5F * this.getAspectFactor(EnumWorldAspect.DESTRUCTION) + 0.5F;
		float goldFactor = 0.5F * this.getAspectFactor(EnumWorldAspect.FORTUNE) + 0.5F;
		float whiteFactor = this.getAspectFactor(EnumWorldAspect.FATE);
		float magentaFactor = 0.5F * this.getAspectFactor(EnumWorldAspect.DREAM) + 0.5F;
		float blackFactor = this.getAspectFactor(EnumWorldAspect.ENTROPY);
		float highFactor = (whiteFactor + 1F) * 0.5F;
		float lowFactor = (blackFactor + 1F) * 0.5F;
		float rgbtotal = redFactor + greenFactor + blueFactor;
		float cmytotal = cyanFactor + magentaFactor + goldFactor;
		float combototal = highFactor + lowFactor;
		float red = ((float)baseFog.x * (redFactor/rgbtotal) * highFactor + lowFactor * cyanFactor / cmytotal) / combototal;
		float green = ((float)baseFog.y * (greenFactor/rgbtotal) * highFactor + lowFactor * magentaFactor / cmytotal) / combototal;
		float blue = ((float)baseFog.z * (blueFactor/rgbtotal) * highFactor + lowFactor * goldFactor / cmytotal) / combototal;
		//float red = MathHelper.clamp((float)baseFog.x * (1+redFactor) * (1+whiteFactor*.33F) * (1-cyanFactor) * (1-blackFactor*.33F), 0F, 1F);
		//float green = MathHelper.clamp((float)baseFog.y * (1+greenFactor) * (1+whiteFactor*.33F) * (1-magentaFactor) * (1-blackFactor*.33F), 0F, 1F);
		//float blue = MathHelper.clamp((float)baseFog.z * (1+blueFactor) * (1+whiteFactor*.33F) * (1-goldFactor) * (1-blackFactor*.33F), 0F, 1F);
		return new Vec3d(red, green, blue);
	}

	@Override
	public float getCloudHeight()
	{
		// TODO Auto-generated method stub
		return super.getCloudHeight();
	}

	@Override
	public boolean isSkyColored()
	{
		// TODO Auto-generated method stub
		return super.isSkyColored();
	}

	@Override
	public BlockPos getSpawnCoordinate()
	{
		// TODO Auto-generated method stub
		return super.getSpawnCoordinate();
	}

	@Override
	public int getAverageGroundLevel()
	{
		// TODO Auto-generated method stub
		return super.getAverageGroundLevel();
	}

	@Override
	public double getVoidFogYFactor()
	{
		// TODO Auto-generated method stub
		return super.getVoidFogYFactor();
	}

	@Override
	public boolean doesXZShowFog(int x, int z)
	{
		// TODO Auto-generated method stub
		return super.doesXZShowFog(x, z);
	}

	@Override
	public BiomeProvider getBiomeProvider()
	{
		// TODO Auto-generated method stub
		return super.getBiomeProvider();
	}

	@Override
	public boolean doesWaterVaporize()
	{
		// TODO Auto-generated method stub
		return super.doesWaterVaporize();
	}

	@Override
	public boolean hasSkyLight()
	{
		// TODO Auto-generated method stub
		return super.hasSkyLight();
	}

	@Override
	public boolean isNether()
	{
		// TODO Auto-generated method stub
		return super.isNether();
	}

	@Override
	public float[] getLightBrightnessTable()
	{
		// TODO Auto-generated method stub
		return super.getLightBrightnessTable();
	}

	@Override
	public WorldBorder createWorldBorder()
	{
		// TODO Auto-generated method stub
		return super.createWorldBorder();
	}

	@Override
	public void setDimension(int dim)
	{
		// TODO Auto-generated method stub
		super.setDimension(dim);
	}

	@Override
	public int getDimension()
	{
		// TODO Auto-generated method stub
		return super.getDimension();
	}

	@Override
	public String getSaveFolder()
	{
		// TODO Auto-generated method stub
		return super.getSaveFolder();
	}

	@Override
	public double getMovementFactor()
	{
		// TODO Auto-generated method stub
		return super.getMovementFactor();
	}

	@Override
	public boolean shouldClientCheckLighting()
	{
		// TODO Auto-generated method stub
		return super.shouldClientCheckLighting();
	}

	@Override
	public IRenderHandler getSkyRenderer()
	{
		// TODO Auto-generated method stub
		return super.getSkyRenderer();
	}

	@Override
	public void setSkyRenderer(IRenderHandler skyRenderer)
	{
		// TODO Auto-generated method stub
		super.setSkyRenderer(skyRenderer);
	}

	@Override
	public IRenderHandler getCloudRenderer()
	{
		// TODO Auto-generated method stub
		return super.getCloudRenderer();
	}

	@Override
	public void setCloudRenderer(IRenderHandler renderer)
	{
		// TODO Auto-generated method stub
		super.setCloudRenderer(renderer);
	}

	@Override
	public IRenderHandler getWeatherRenderer()
	{
		// TODO Auto-generated method stub
		return super.getWeatherRenderer();
	}

	@Override
	public void setWeatherRenderer(IRenderHandler renderer)
	{
		// TODO Auto-generated method stub
		super.setWeatherRenderer(renderer);
	}

	@Override
	public void getLightmapColors(float partialTicks, float sunBrightness, float skyLight, float blockLight,
			float[] colors)
	{
		// TODO Auto-generated method stub
		super.getLightmapColors(partialTicks, sunBrightness, skyLight, blockLight, colors);
	}

	@Override
	public BlockPos getRandomizedSpawnPoint()
	{
		// TODO Auto-generated method stub
		return super.getRandomizedSpawnPoint();
	}

	@Override
	public boolean shouldMapSpin(String entity, double x, double z, double rotation)
	{
		// TODO Auto-generated method stub
		return super.shouldMapSpin(entity, x, z, rotation);
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		// TODO Auto-generated method stub
		return super.getRespawnDimension(player);
	}

	@Override
	public ICapabilityProvider initCapabilities()
	{
		// TODO Auto-generated method stub
		return super.initCapabilities();
	}

	@Override
	public MusicType getMusicType()
	{
		// TODO Auto-generated method stub
		return super.getMusicType();
	}

	@Override
	public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos)
	{
		// TODO Auto-generated method stub
		return super.canSleepAt(player, pos);
	}

	@Override
	public Biome getBiomeForCoords(BlockPos pos)
	{
		// TODO Auto-generated method stub
		return super.getBiomeForCoords(pos);
	}

	@Override
	public boolean isDaytime()
	{
		// TODO Auto-generated method stub
		return super.isDaytime();
	}

	@Override
	public float getSunBrightnessFactor(float par1)
	{
		// TODO Auto-generated method stub
		return super.getSunBrightnessFactor(par1);
	}

	@Override
	public float getCurrentMoonPhaseFactor()
	{
		// TODO Auto-generated method stub
		return super.getCurrentMoonPhaseFactor();
	}

	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks)
	{
		// TODO Auto-generated method stub
		return super.getSkyColor(cameraEntity, partialTicks);
	}

	@Override
	public Vec3d getCloudColor(float partialTicks)
	{
		// TODO Auto-generated method stub
		return super.getCloudColor(partialTicks);
	}

	@Override
	public float getSunBrightness(float par1)
	{
		// TODO Auto-generated method stub
		return super.getSunBrightness(par1);
	}

	@Override
	public float getStarBrightness(float par1)
	{
		// TODO Auto-generated method stub
		return super.getStarBrightness(par1);
	}

	@Override
	public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
	{
		// TODO Auto-generated method stub
		super.setAllowedSpawnTypes(allowHostile, allowPeaceful);
	}

	@Override
	public void calculateInitialWeather()
	{
		// TODO Auto-generated method stub
		super.calculateInitialWeather();
	}

	@Override
	public void updateWeather()
	{
		// TODO Auto-generated method stub
		super.updateWeather();
	}

	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater)
	{
		// TODO Auto-generated method stub
		return super.canBlockFreeze(pos, byWater);
	}

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight)
	{
		// TODO Auto-generated method stub
		return super.canSnowAt(pos, checkLight);
	}

	@Override
	public void setWorldTime(long time)
	{
		// TODO Auto-generated method stub
		super.setWorldTime(time);
	}

	@Override
	public long getWorldTime()
	{
		// TODO Auto-generated method stub
		return super.getWorldTime();
	}

	@Override
	public BlockPos getSpawnPoint()
	{
		// TODO Auto-generated method stub
		return super.getSpawnPoint();
	}

	@Override
	public void setSpawnPoint(BlockPos pos)
	{
		// TODO Auto-generated method stub
		super.setSpawnPoint(pos);
	}

	@Override
	public boolean canMineBlock(EntityPlayer player, BlockPos pos)
	{
		// TODO Auto-generated method stub
		return super.canMineBlock(player, pos);
	}

	@Override
	public boolean isBlockHighHumidity(BlockPos pos)
	{
		// TODO Auto-generated method stub
		return super.isBlockHighHumidity(pos);
	}

	@Override
	public int getHeight()
	{
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public int getActualHeight()
	{
		// TODO Auto-generated method stub
		return super.getActualHeight();
	}

	@Override
	public double getHorizon()
	{
		// TODO Auto-generated method stub
		return super.getHorizon();
	}

	@Override
	public void resetRainAndThunder()
	{
		// TODO Auto-generated method stub
		super.resetRainAndThunder();
	}

	@Override
	public boolean canDoLightning(Chunk chunk)
	{
		// TODO Auto-generated method stub
		return super.canDoLightning(chunk);
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		// TODO Auto-generated method stub
		return super.canDoRainSnowIce(chunk);
	}

	@Override
	public void onPlayerAdded(EntityPlayerMP player)
	{
		// TODO Auto-generated method stub
		super.onPlayerAdded(player);
	}

	@Override
	public void onPlayerRemoved(EntityPlayerMP player)
	{
		// TODO Auto-generated method stub
		super.onPlayerRemoved(player);
	}

	@Override
	public void onWorldSave()
	{
		// TODO Auto-generated method stub
		super.onWorldSave();
	}

	@Override
	public void onWorldUpdateEntities()
	{
		// TODO Auto-generated method stub
		super.onWorldUpdateEntities();
	}

	@Override
	public boolean canDropChunk(int x, int z)
	{
		// TODO Auto-generated method stub
		return super.canDropChunk(x, z);
	}
    
    
}
