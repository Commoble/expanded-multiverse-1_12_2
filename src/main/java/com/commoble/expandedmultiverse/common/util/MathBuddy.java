package com.commoble.expandedmultiverse.common.util;

public class MathBuddy
{
	public static final float TWOPI = 2F * (float)Math.PI;
	
	// ticks per second = 20
	// ticks * 0.05 seconds per tick = seconds
	// rps * seconds = rotations
	// 2PI rads per rotation * rotations = angle in rads
	// angle = t * 0.05 * rps * 2PI
	// rps can be set as needed
	public static final float RADS_PER_TICK = 0.05F * TWOPI;
}
