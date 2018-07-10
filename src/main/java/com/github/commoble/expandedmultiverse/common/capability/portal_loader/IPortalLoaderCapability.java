package com.commoble.expandedmultiverse.common.capability.portal_loader;

public interface IPortalLoaderCapability
{
	/**
	 * @return The ticks corresponding to the amount of time the player has spent in a wormhole portal
	 */
	public int getTicksInPortal();
	
	/**
	 * Sets the total tick value to a specific number
	 * @param ticks to set the value to
	 */
	public void setTicksInPortal(int ticks);
	
	/**
	 * Adds a value to the capability's tick counter
	 * @param ticks (make this negative to subtract)
	 */
	public void addTicksInPortal(int ticks);
	/**
	 * 
	 * @return The ticks corresponding to the cooldown time after exiting a portal before a teleport can occur again
	 */
	public int getCooldownTicks();
	
	/**
	 * Sets the total cooldown tick value to a specific number
	 * @param ticks to set the value to
	 */
	public void setCooldownTicks(int ticks);
	
	/**
	 * Adds a value to cooldown counter
	 * @param ticks (make this negative to subtract)
	 */
	public void addCooldownTicks(int ticks);

}
