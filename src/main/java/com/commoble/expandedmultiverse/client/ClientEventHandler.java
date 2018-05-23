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
		// regular items
		registerItemRenderer(ItemLedger.spyglassItem);
		
		// itemblocks
		registerItemblockRenderer(ItemLedger.wormholeCoreItemBlock);
		registerItemblockRenderer(ItemLedger.stabilizerItemBlock);
	}
	
	private static void registerItemRenderer(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void registerItemblockRenderer(ItemBlock iblock)
	{
		ModelLoader.setCustomModelResourceLocation(iblock, 0, new ModelResourceLocation(iblock.getBlock().getRegistryName(), "inventory"));
	}
}
