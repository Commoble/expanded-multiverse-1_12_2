package com.commoble.expandedmultiverse.common.block;

import com.commoble.expandedmultiverse.common.tileentity.TileEntityWormholeCore;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWormholeCore extends BlockContainer
{

	public BlockWormholeCore()
	{
		super(Material.PORTAL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO Auto-generated method stub
		return new TileEntityWormholeCore();
	}

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return false;
    }

}
