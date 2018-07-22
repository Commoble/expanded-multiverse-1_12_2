package com.github.commoble.expandedmultiverse.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleWormholeCore extends Particle
{	
	public static final float SCALE_ACCELERATION = -0.2F;	// rate at which shrinking rate accelerates (per tick)
	
	public float current_scale_vel;
	
	public ParticleWormholeCore(World world, double posXIn, double posYIn, double posZIn)
	{
		super(world, posXIn, posYIn, posZIn);
		// end rod particle indices are 176-183
		this.setParticleTextureIndex(176 + world.rand.nextInt(7));
		this.setAlphaF(world.rand.nextFloat()*0.9F);
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleMaxAge = 10 + this.rand.nextInt(10);	// 10 to 20
        this.setAlphaF(1F);
        this.particleScale = 5F + this.rand.nextFloat()*10F;	// 5 to 15F
        this.current_scale_vel = this.rand.nextFloat();	// 0 to 1F
        float brightness = 0.9F + world.rand.nextFloat()*0.1F;
        this.setRBGColorF(brightness, brightness, brightness);
	}

    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge || this.particleScale < 0.1F)
        {
            this.setExpired();
        }
        this.setAlphaF(1F - ((float)this.particleAge / (float)this.particleMaxAge));
        this.particleScale += this.current_scale_vel;
        this.current_scale_vel += SCALE_ACCELERATION;
    }

}
