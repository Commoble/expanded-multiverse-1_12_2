package com.github.commoble.expandedmultiverse.common.capability.portal_loader;

public class PortalLoaderCapability implements IPortalLoaderCapability
{
	/**
	 * How long the player has been standing in a portal
	 */
	protected int ticksInPortal;
	
	/**
	 * Used for
	 * A) both preventing ticksInPortal from being decremented while the player is standing in a wormhole
	 * 		--cooldownTicks will be positive
	 * B) preventing a teleport from initiating for a short period after teleporting from another wormhole
	 * 		-- cooldownTicks will be negative
	 * this initiates to the reset constant so that packets don't have to be sent to newly
	 * created player entities in the destination world
	 */
	protected int cooldownTicks = -TICKS_TO_RESET_TELEPORT;
	
	/**
	 * How many ticks the player must stand in the wormhole for to initiate a teleport
	 */
	public static final int TICKS_TO_INITIATE_TELEPORT = 100;
	
	/**
	 * If the player leaves a wormhole before the teleport initiates, the game will
	 * wait this many ticks before it starts decrementing from ticksInPortal
	 */
	public static final int TICKS_BEFORE_EFFECT_DISSIPATES = 5;
	
	/**
	 * After a teleport, the player must spend this many ticks outside of a wormhole before the teleport can initiate
	 */
	public static final int TICKS_TO_RESET_TELEPORT = 30;
	

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

	@Override
	public int getCooldownTicks()
	{
		// TODO Auto-generated method stub
		return this.cooldownTicks;
	}

	@Override
	public void setCooldownTicks(int ticks)
	{
		this.cooldownTicks = ticks;
	}

	@Override
	public void addCooldownTicks(int ticks)
	{
		this.cooldownTicks += ticks;
	}

}
