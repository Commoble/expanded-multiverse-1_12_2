package com.commoble.expandedmultiverse.common.block;

import com.commoble.expandedmultiverse.common.item.ItemLedger;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockStabilizer extends Block
{
	public BlockStabilizer()
	{
		super(Material.WOOD);
		this.setCreativeTab(ItemLedger.emtab);
		this.setSoundType(SoundType.WOOD);
	}
	
	public boolean onBlockActivated()
	{
		return false;
	}
}
