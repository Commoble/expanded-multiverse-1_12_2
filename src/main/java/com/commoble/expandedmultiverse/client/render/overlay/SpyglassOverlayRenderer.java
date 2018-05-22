package com.commoble.expandedmultiverse.client.render.overlay;

import org.lwjgl.opengl.GL11;

import com.commoble.expandedmultiverse.client.render.RenderBuddy;
import com.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class SpyglassOverlayRenderer
{
	// the texture that restricts the player's view
	private final static ResourceLocation spyglassOverlay =
			new ResourceLocation(ExpandedMultiverseMod.MODID, "textures/overlay/scope_full.png");
	private final static ResourceLocation spyglassMask =
			new ResourceLocation(ExpandedMultiverseMod.MODID, "textures/overlay/scope_mask.png");
	
	private static final int OVERLAY_SIZE = 256;
	
	
	public static void render(Minecraft mc, int screenWidth, int screenHeight)
	{
		Tessellator.getInstance().getBuffer();
		// save the current state of OpenGL to restore it later
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPushMatrix();
		
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		
		// set color to black
		GL11.glColor4f(1F,  1F,  1F,  1F);
		mc.renderEngine.bindTexture(spyglassMask);
		
		// start from top-left of screen
		float scalefactor;
		if (screenWidth > screenHeight)
		{
			scalefactor = (float)screenHeight / (float)OVERLAY_SIZE;
			//GL11.glTranslatef((screenWidth / 2) - OVERLAY_SIZE / 2, 0, 0);
		}
		else
		{
			scalefactor = (float)screenWidth / (float)OVERLAY_SIZE;
			//GL11.glTranslatef(0F, (screenHeight / 2) - OVERLAY_SIZE / 2, 0F);
		}
		
		//GL11.glScalef(scalefactor, scalefactor, 1F);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(0.0D, (double)screenHeight, -90.0D).tex(0.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)screenWidth, (double)screenHeight, -90.0D).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)screenWidth, 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
		
		// scale scope image to smaller of the two screen dimensions
		//RenderBuddy.drawTexturedModalRect(0, 0, 0, 0, OVERLAY_SIZE, OVERLAY_SIZE, 100D);
		
		
		// restore the state of OpenGL
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}
}
