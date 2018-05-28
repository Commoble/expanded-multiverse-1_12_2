package com.commoble.expandedmultiverse.common.capability.portal_loader;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class PortalLoaderProvider implements ICapabilityProvider
{
	@CapabilityInject(IPortalLoaderCapability.class)
	public static final Capability<IPortalLoaderCapability> PORTAL_LOADER_CAP = null;
	
	private IPortalLoaderCapability instance = PORTAL_LOADER_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		// TODO Auto-generated method stub
		return capability == PORTAL_LOADER_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		// TODO Auto-generated method stub
		return capability == PORTAL_LOADER_CAP ? PORTAL_LOADER_CAP.<T> cast(this.instance) : null;
	}

}
