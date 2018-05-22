package com.commoble.expandedmultiverse.client.render.overlay;

import org.lwjgl.opengl.GL11;

import com.commoble.expandedmultiverse.client.render.RenderHelper;
import com.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class SpyglassOverlayRenderer
{
	// the texture that restricts the player's view
	private final static ResourceLocation spyglassOverlay =
			new ResourceLocation(ExpandedMultiverseMod.MODID, "textures/overlay/scope_full.png");
	
	private static final int OVERLAY_SIZE = 256;
	
	
	public static void render(Minecraft mc, int screenWidth, int screenHeight)
	{
		// save the current state of OpenGL to restore it later
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glPushMatrix();
		
		// set color to black
		GL11.glColor4f(1.0F,  1.0F,  1.0F,  1.0F);
		mc.renderEngine.bindTexture(spyglassOverlay);
		
		// start from top-left of screen
		GL11.glTranslatef(0, 0, 0);
		RenderHelper.drawTexturedModalRect(0, 0, 0, 0, OVERLAY_SIZE, OVERLAY_SIZE, 100D);
		
		// restore the state of OpenGL
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}
}
