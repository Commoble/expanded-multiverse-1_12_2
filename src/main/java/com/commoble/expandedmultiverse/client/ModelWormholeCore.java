package com.commoble.expandedmultiverse.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWormholeCore extends ModelBase
{
	public ModelRenderer model;
	
	public ModelWormholeCore()
	{
		this.model = new ModelRenderer(this).setTextureOffset(0, 0).addBox(-8F, 0F, -8F, 16, 16, 16);
		this.model.setRotationPoint(8F, 0F, 8F);
	}
	
	public void render(float angle)
	{
		this.model.rotateAngleY = angle;
		this.model.render(0.0625F);
	}
}
