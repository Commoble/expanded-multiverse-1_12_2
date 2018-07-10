package com.github.commoble.expandedmultiverse.common.world;

public enum EnumWorldAspect
{
	CREATION,
	LIFE,
	DEATH,
	DESTRUCTION,
	FORTUNE,
	FATE,
	DREAM,
	ENTROPY;
	
	public static final int COUNT = 8;	// 8 aspects
	public static final int CEILING_FOR_AVERAGE = 16;	// -16 to 15 aspect is average
	public static final int CEILING_FOR_ABOVE_AVERAGE = 32;	// -17 to -32 and 16 to 31 aspect is above average
	public static final int CEILING_FOR_HIGH = 64; // -33 to -64 and 32 to 63 aspect is high
	public static final int MIN = -128;
	public static final int MAX = 128;	// -65 to -128 and 64 to 127 is very high
}
