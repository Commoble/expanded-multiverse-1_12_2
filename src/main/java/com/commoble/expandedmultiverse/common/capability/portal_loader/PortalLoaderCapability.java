package com.commoble.expandedmultiverse.common.capability.portal_loader;

public class PortalLoaderCapability implements IPortalLoaderCapability
{
	protected int ticksInPortal;

	@Override
	public int getTicksInPortal()
	{
		// TODO Auto-generated method stub
		return this.ticksInPortal;
	}

	@Override
	public void setTicksInPortal(int ticks)
	{
		this.ticksInPortal = ticks;
	}

	@Override
	public void addTicksInPortal(int ticks)
	{
		this.ticksInPortal += ticks;
	}

}
