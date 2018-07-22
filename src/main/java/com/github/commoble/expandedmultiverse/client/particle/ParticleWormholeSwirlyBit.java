package com.github.commoble.expandedmultiverse.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleWormholeSwirlyBit extends Particle
{
	public static final int FIRST_TEXTURE_INDEX = 176;	// we're using ender rod stars again
	public static final int TEXTURE_INDEX_COUNT = 7;
	
	// center-seeking acceleration
	// particle velocity will be nudged toward the center of the wormhole each tick
	// this value controls the acceleration rate
	public static final double CENTRIPETAL_ACCELERATION = 0.01F;
	
	// coordinates in entityspace of the center of the wormhole block the particle is swirling around
	public double xCenter;
	public double yCenter;
	public double zCenter;
	
	// the latter three doubles are the coordinates of the center of the wormhole in entityspace
	// i.e. the blockpos with 0.5D added to each integer coordinate
	public ParticleWormholeSwirlyBit(World world, double posXIn, double posYIn, double posZIn, double xCenter, double yCenter, double zCenter)
	{
		super(world, posXIn, posYIn, posZIn);
		// end rod particle indices are 176-183
		this.setParticleTextureIndex(FIRST_TEXTURE_INDEX + world.rand.nextInt(TEXTURE_INDEX_COUNT));
        this.motionX = -0.1D + world.rand.nextDouble() * 0.2D;
        this.motionY = -0.1D + world.rand.nextDouble() * 0.2D;
        this.motionZ = -0.1D + world.rand.nextDouble() * 0.2D;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.zCenter = zCenter;
        this.particleMaxAge = 20 + this.rand.nextInt(20);	// 10 to 20
        this.setAlphaF(1F);
        this.particleScale = 1F;
        float red = 0.8F + world.rand.nextFloat()*0.2F;
        float blue = 0.8F + world.rand.nextFloat()*0.2F;
        float green = 0.8F + world.rand.nextFloat()*0.2F;
        this.setRBGColorF(red, blue, green);
        this.canCollide = false;
	}

    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
        
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        // get vector pointing to center of wormhole
        double rx = this.xCenter - this.posX;
        double ry = this.yCenter - this.posY;
        double rz = this.zCenter - this.posZ;
        
        if (rx*rx + ry*ry + rz*rz < 0.2D)
        {
        	this.setExpired();
        	return;
        }
        
        // use that as acceleration vector (multiply by accel coefficient and add to velocity vector)
        this.motionX += rx * CENTRIPETAL_ACCELERATION;
        this.motionY += ry * CENTRIPETAL_ACCELERATION;
        this.motionZ += rz * CENTRIPETAL_ACCELERATION;
        

        this.move(this.motionX, this.motionY, this.motionZ);

//        if (this.onGround)
//        {
//            this.motionX *= 0.699999988079071D;
//            this.motionZ *= 0.699999988079071D;
//        }
    }
}
