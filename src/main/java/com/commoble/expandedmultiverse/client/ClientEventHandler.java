package com.commoble.expandedmultiverse.client;

import com.commoble.expandedmultiverse.common.item.ItemLedger;

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
		registerItemblockRenderer(ItemLedger.wormholeCoreItemBlock);
	}
	
	private static void registerItemblockRenderer(ItemBlock iblock)
	{
		ModelLoader.setCustomModelResourceLocation(iblock, 0, new ModelResourceLocation(iblock.getBlock().getRegistryName(), "inventory"));
	}
}
