package com.commoble.expandedmultiverse.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWormholeCore extends ModelBase
{
	public ModelRenderer model;
	
	public ModelWormholeCore()
	{
		this.model = new ModelRenderer(this).setTextureOffset(0, 0).addBox(0, 0, 0, 1, 1, 1);
		this.model.setRotationPoint(0.5F, 0F, 0F);
	}
	
	public void render(float angle)
	{
		this.model.rotateAngleY = angle;
		this.model.render(1F);
	}
}
