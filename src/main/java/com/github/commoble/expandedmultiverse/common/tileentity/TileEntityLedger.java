package com.github.commoble.expandedmultiverse.common.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLedger
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityWormholeCore.class, "te_wormholecore");
	}
}
