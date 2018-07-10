package com.commoble.expandedmultiverse.client.render.tesr;

import com.commoble.expandedmultiverse.client.model.ModelWormholeCore;
import com.commoble.expandedmultiverse.common.item.ItemLedger;
import com.commoble.expandedmultiverse.common.tileentity.TileEntityWormholeCore;
import com.commoble.expandedmultiverse.common.util.MathBuddy;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class TESRWormholeCore extends TileEntitySpecialRenderer<TileEntityWormholeCore>
{
	public static final ResourceLocation TEXTURE_WORMHOLE = new ResourceLocation("expandedmultiverse:textures/blocks/wormholecore_expanded.png");
	private final ModelWormholeCore model = new ModelWormholeCore();
	public static final float ROTATIONS_PER_SECOND = 1F;
	
	@Override
	public void render(TileEntityWormholeCore te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		IBlockState iblockstate = Blocks.GOLD_BLOCK.getDefaultState();
        
		if (te.getIsActive() || (player != null && player.getHeldItemMainhand().getItem() == ItemLedger.spyglassItem))
		{	// if inactive, player must be holding a spyglass to see this
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


		else if (iblockstate != null)
        {
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
			GlStateManager.translate((float)x,  (float)y,  (float)z + 1F);
//            GlStateManager.translate(0.0F, 0.6875F, -0.75F);
//            GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
//            GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
//            GlStateManager.translate(0.25F, 0.1875F, 0.25F);
            float f = 0.5F;
//            GlStateManager.scale(-0.5F, -0.5F, 0.5F);
            int i = te.getWorld().getCombinedLight(new BlockPos(Math.floor(x), Math.floor(y), Math.floor(z)), 0);
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            blockrendererdispatcher.renderBlockBrightness(iblockstate, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
	}
}
