package com.github.commoble.expandedmultiverse.common.capability.portal_loader;

import java.util.concurrent.Callable;

public class PortalLoaderFactory implements Callable<IPortalLoaderCapability>
{

	@Override
	public IPortalLoaderCapability call() throws Exception
	{
		// TODO Auto-generated method stub
		return new PortalLoaderCapability();
	}

}
