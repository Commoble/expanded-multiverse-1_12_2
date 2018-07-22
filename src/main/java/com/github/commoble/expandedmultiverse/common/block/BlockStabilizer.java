package com.github.commoble.expandedmultiverse.common.block;

import com.github.commoble.expandedmultiverse.common.item.ItemTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockStabilizer extends Block
{
	public BlockStabilizer()
	{
		super(Material.WOOD);
		this.setCreativeTab(ItemTabs.emtab);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		// if there's a wormhole correctly positioned above this block, attempt to activate it
		if (world.getBlockState(pos.up()).getBlock() == Blocks.AIR && world.getBlockState(pos.up(2)).getBlock() == BlockRegistrar.wormhole_core)
		{
			BlockWormholeCore.activateWormhole(world, pos.up(2));
		}
		
		return true;
	}
}
