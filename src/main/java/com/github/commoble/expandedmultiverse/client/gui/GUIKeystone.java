package com.github.commoble.expandedmultiverse.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class GUIKeystone extends GuiContainer
{

	public GUIKeystone(Container inventorySlotsIn)
	{
		super(inventorySlotsIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		// TODO Auto-generated method stub
		
	}
//	public GUIKeystone(InventoryPlayer inventoryPlayer, IVTileEntityKeystone tileEntity)
//	{
//		super(new IVContainerKeystone(inventoryPlayer, tileEntity));
//	}
//	
//	@Override
//    protected void drawGuiContainerForegroundLayer(int par1, int par2)
//	{
//        //the parameters for drawString are: string, x, y, color
//        fontRenderer.drawString("Astral Keystone", 8, 6, 4210752);
//        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
//    }
//
//	@Override
//	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
//	{
//		int texture = mc.renderEngine.getTexture("/ivresources/gui/keystone.png");
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        this.mc.renderEngine.bindTexture(texture);
//        int x = (width - xSize) / 2;
//        int y = (height - ySize) / 2;
//        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
//	}
}
