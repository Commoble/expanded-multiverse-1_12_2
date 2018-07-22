package com.github.commoble.expandedmultiverse.common.item;

import com.github.commoble.expandedmultiverse.common.ExpandedMultiverseMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Class that handles creative tabs for keeping the items in
 * @author Joseph
 *
 */
public class ItemTabs
{
	// creative tab for the stuff
	public static final CreativeTabs emtab = new CreativeTabs(ExpandedMultiverseMod.MODID) {
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Items.ENDER_EYE);
		}
	};
}
