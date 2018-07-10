package com.github.commoble.expandedmultiverse.common.network;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ExpandedMultiverseMod.MODID);
	
	
}
