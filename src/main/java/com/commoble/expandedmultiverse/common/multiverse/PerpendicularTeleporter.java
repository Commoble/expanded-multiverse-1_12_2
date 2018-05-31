package com.commoble.expandedmultiverse.common.multiverse;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.commoble.expandedmultiverse.common.block.BlockLedger;
import com.commoble.expandedmultiverse.common.world.WorldHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

/**
 * Teleporter for sending things to a perpendicular universe
 * @author Joseph
 *
 */
public class PerpendicularTeleporter implements ITeleporter
{
	private final BlockPos basePos;	// the blockpos of the block that initiated the teleport
	
    public PerpendicularTeleporter(BlockPos pos)
    {
    	this.basePos = pos;
    }

    /**
     * Called to handle placing the entity in the new nextWorld.
     *
     * The initial position of the entity will be its
     * position in the origin nextWorld, multiplied horizontally
     * by the computed cross-dimensional movement factor
     * (see {@link WorldProvider#getMovementFactor()}).
     *
     * Note that the supplied entity has not yet been spawned
     * in the destination nextWorld at the time.
     *
     * @param nextWorld  the entity's destination
     * @param entity the entity to be placed
     * @param yaw    the suggested yaw value to apply
     */
    @Override
    public void placeEntity(World nextWorld, Entity entity, float yaw)
    {
    	if (!entity.world.isRemote)
    	{	// don't run on the client
            placeInPortal(nextWorld, entity, yaw);
            nextWorld.notifyNeighborsOfStateChange(this.basePos, nextWorld.getBlockState(this.basePos).getBlock(), false);
    	}
    }

    public void placeInPortal(World nextWorld, Entity entityIn, float rotationYaw)
    {
        if (!this.attemptToPlaceInExistingPortal(nextWorld, entityIn, rotationYaw))
        {
            BlockPos pos = this.makePortal(nextWorld, entityIn);
            this.definitelyPlaceInExistingPortal(entityIn, rotationYaw, pos);
        }
    }
    
    // creates a portal at the entity's location
    // in the nextWorld the entity is traveling to
    // returns the BlockPos of the portal block that was created
    private BlockPos makePortal(World nextWorld, Entity entityIn)
    {
    	int xBase = this.basePos.getX();
    	int yBase = this.basePos.getY();
    	int zBase = this.basePos.getZ();
    	
    	WorldHelper.setBlocksInRect(Blocks.AIR.getDefaultState(), nextWorld, xBase-3, yBase-3, zBase-3, xBase+3, yBase+3, zBase+3);
    	BlockPos pos = new BlockPos(xBase, yBase, zBase);
    	nextWorld.setBlockState(pos, BlockLedger.blockWormholeCore.getDefaultState());
    	return pos;
    }
    
    /**
     * Searches for a portal location and attempts to spawn the entity there
     * @param ent
     * @param rotationYaw
     * @return true if successful, false if not
     */
    public boolean attemptToPlaceInExistingPortal(World nextWorld, Entity ent, float rotationYaw)
    {
    	// try to get the location of an existing portal
    	BlockPos pos = this.getExistingPortalLocation(nextWorld);
    	if (pos != null)
    	{	// if a portal exists at a reasonable location, place the entity there
    		this.definitelyPlaceInExistingPortal(ent, rotationYaw, pos);
    		return true;
    	}
    	else
    	{	// if no such location exists
    		return false;
    	}
    }
    
    public void definitelyPlaceInExistingPortal(Entity ent, float rotationYaw, BlockPos pos)
    {
    	double x = (double)pos.getX() + 0.5D;
    	double y = (double)pos.getY() + 0.5D;
    	double z = (double)pos.getZ() + 0.5D;
    	
        ent.motionX = 0.0D;
        ent.motionY = 0.0D;
        ent.motionZ = 0.0D;
    	
    	if (ent instanceof EntityPlayerMP)
    	{
            ((EntityPlayerMP)ent).connection.setPlayerLocation(x, y, z, ent.rotationYaw, ent.rotationPitch);
    	}
    	else
    	{
            ent.setLocationAndAngles(x, y, z, ent.rotationYaw, ent.rotationPitch);
    	}
        
    }
    
    /**
     * 
     * @param ent The entity being teleported
     * @return	A BlockPos corresponding to the location of an existing portal (in the nextWorld being teleported to), or null
     */
    @Nullable
    public BlockPos getExistingPortalLocation(World nextWorld)
    {
    	/*
    	int xBase = this.basePos.getX();
    	int yBase = this.basePos.getY();
    	int zBase = this.basePos.getZ();
    	
    	int xStart = xBase-5;
    	int xEnd = xBase+5;
    	int zStart = zBase-5;
    	int zEnd = zBase+5;
    	
    	int yStart = (yBase-5 > 0) ? yBase-5 : 0;
    	int yEnd = (yBase+5 < nextWorld.getHeight() - 1) ? yBase+5 : nextWorld.getHeight();
    	// just check in a cube around the point for now
    	for (int x=xStart; x<=xEnd; x++)
    	{
    		for (int y=yStart; y<=yEnd; y++)
    		{
    			for (int z=zStart; z<=zEnd; z++)
    			{
    				BlockPos checkPos = new BlockPos(x,y,z);
    				if (nextWorld.getBlockState(new BlockPos(x,y,z)).getBlock() == BlockLedger.blockWormholeCore)
    				{
    					return checkPos;
    				}
    			}
    		}
    	}*/
    	
    	if (nextWorld.getBlockState(this.basePos).getBlock() == BlockLedger.blockWormholeCore)
    	{
    		return this.basePos;
    	}
    	
    	// no portal pos found
    	return null;
    }

}
