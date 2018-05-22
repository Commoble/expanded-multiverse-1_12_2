package com.commoble.expandedmultiverse.client;

import com.commoble.expandedmultiverse.common.item.ItemLedger;
import com.commoble.expandedmultiverse.common.tileentity.TileEntityWormholeCore;
import com.commoble.expandedmultiverse.common.util.MathBuddy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TESRWormholeCore extends TileEntitySpecialRenderer<TileEntityWormholeCore>
{
	public static final ResourceLocation TEXTURE_WORMHOLE = new ResourceLocation("expandedmultiverse:textures/blocks/wormholecore_expanded.png");
	private final ModelWormholeCore model = new ModelWormholeCore();
	public static final float ROTATIONS_PER_SECOND = 1F;
	
	@Override
	public void render(TileEntityWormholeCore te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if (player != null && player.getHeldItemMainhand().getItem() == ItemLedger.spyglassItem)
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate((float)x,  (float)y,  (float)z);
			
			float ticks = te.getWorld().getWorldTime() + partialTicks;
			this.bindTexture(this.TEXTURE_WORMHOLE);
			float angle = (ticks * ROTATIONS_PER_SECOND * MathBuddy.RADS_PER_TICK) % MathBuddy.TWOPI;
			
			GlStateManager.enableCull();
			model.render(angle);
			//GlStateManager.disableCull();
			GlStateManager.popMatrix();
		}
	}
}
