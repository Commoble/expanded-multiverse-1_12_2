package com.github.commoble.expandedmultiverse.common.block;

import com.github.commoble.expandedmultiverse.common.item.ItemTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockKeystone extends Block
{
	public BlockKeystone()
	{
		super(Material.ROCK);
		this.setCreativeTab(ItemTabs.emtab);
		this.setSoundType(SoundType.STONE);
		this.setHardness(10.0F);
		this.setResistance(2000.0F);
	}
}
