package com.commoble.expandedmultiverse.common.capability.portal_loader;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

// storage for portal loader capability
public class PortalLoaderStorage implements Capability.IStorage<IPortalLoaderCapability>
{
	// the Portal Loader doesn't need to know anything for more than a few seconds at a time
	// so there's no point in writing to the disk
	
	@Override
	public NBTBase writeNBT(Capability<IPortalLoaderCapability> capability, IPortalLoaderCapability instance,
			EnumFacing side)
	{
		return null;
	}

	@Override
	public void readNBT(Capability<IPortalLoaderCapability> capability, IPortalLoaderCapability instance,
			EnumFacing side, NBTBase nbt)
	{
		
	}

}
