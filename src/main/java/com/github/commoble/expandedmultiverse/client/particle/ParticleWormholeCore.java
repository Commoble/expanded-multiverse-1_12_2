package com.github.commoble.expandedmultiverse.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleWormholeCore extends Particle
{
	public static final float SCALE_PER_TICKS_REMAINING = 0.75F;
	boolean reversed;	// if true, particles will expand; if false, particles will contract
	
	public ParticleWormholeCore(World world, double posXIn, double posYIn, double posZIn)
	{
		super(world, posXIn, posYIn, posZIn);
		this.reversed = world.rand.nextBoolean();
		// end rod particle indices are 176-183
		this.setParticleTextureIndex(176 + world.rand.nextInt(7));
		this.setAlphaF(world.rand.nextFloat()*0.9F);
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleMaxAge = 10 + this.rand.nextInt(10);
        this.particleScale = this.reversed ? 0F : this.particleMaxAge * SCALE_PER_TICKS_REMAINING;
        float brightness = 0.9F + world.rand.nextFloat()*0.1F;
        this.setRBGColorF(brightness, brightness, brightness);
	}

    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
        
        this.particleScale = this.reversed ? this.particleAge * SCALE_PER_TICKS_REMAINING : (this.particleMaxAge - this.particleAge) * SCALE_PER_TICKS_REMAINING;
    }

}
