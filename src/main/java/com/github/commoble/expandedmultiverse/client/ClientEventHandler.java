package com.github.commoble.expandedmultiverse.client;

import com.github.commoble.expandedmultiverse.common.item.ItemRegistrar;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientEventHandler
{
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent e)
	{
		// regular items
		registerItemRenderer(ItemRegistrar.spyglass);
		
		// itemblocks
		registerItemRenderer(ItemRegistrar.wormhole_core);
		registerItemRenderer(ItemRegistrar.stabilizer);
	}
	
	private static void registerItemRenderer(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
