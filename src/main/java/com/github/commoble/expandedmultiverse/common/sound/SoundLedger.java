package com.github.commoble.expandedmultiverse.common.sound;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class SoundLedger
{
	
	public static void registerSounds()
	{
		
	}
	
	public static SoundEvent registerSound(String name)
	{
		ResourceLocation loc = new ResourceLocation(ExpandedMultiverseMod.MODID, name);
		return new SoundEvent(loc);
	}
}
