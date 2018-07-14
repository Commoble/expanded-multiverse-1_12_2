package com.github.commoble.expandedmultiverse.common.tileentity;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLedger
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityWormholeCore.class, new ResourceLocation(ExpandedMultiverseMod.MODID, "te_wormholecore"));
	}
}
