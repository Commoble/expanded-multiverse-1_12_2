package com.commoble.expandedmultiverse.multiverse;

import java.util.Random;

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
	private WorldServer world;	// the world that the player is teleporting *into*
	private Random random;
	
    public PerpendicularTeleporter(WorldServer worldIn)
    {
        this.world = worldIn;
        this.random = new Random(worldIn.getSeed());
    }

    /**
     * Called to handle placing the entity in the new world.
     *
     * The initial position of the entity will be its
     * position in the origin world, multiplied horizontally
     * by the computed cross-dimensional movement factor
     * (see {@link WorldProvider#getMovementFactor()}).
     *
     * Note that the supplied entity has not yet been spawned
     * in the destination world at the time.
     *
     * @param world  the entity's destination
     * @param entity the entity to be placed
     * @param yaw    the suggested yaw value to apply
     */
    @Override
    public void placeEntity(World world, Entity entity, float yaw)
    {
        if (entity instanceof EntityPlayerMP)
            placeInPortal((EntityPlayerMP)entity, yaw);
        else
            placeInExistingPortal(entity, yaw);
    }

    public void placeInPortal(EntityPlayerMP entityIn, float rotationYaw)
    {
        if (this.world.provider.getDimensionType().getId() != 1)
        {
            if (!this.placeInExistingPortal(entityIn, rotationYaw))
            {
                this.makePortal(entityIn);
                this.placeInExistingPortal(entityIn, rotationYaw);
            }
        }
    }

}
