package com.commoble.expandedmultiverse.common;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

@Mod(modid = ExpandedMultiverseMod.MODID, version = ExpandedMultiverseMod.VERSION, name=ExpandedMultiverseMod.NAME)
public class ExpandedMultiverseMod
{
	@Instance("expandedmultiverse")	// the static instance of the mod class
	public static ExpandedMultiverseMod instance = new ExpandedMultiverseMod();
	
    public static final String MODID = "expandedmultiverse";
    public static final String VERSION = "1.0.0.0";
    public static final String NAME="Expanded Multiverse";
    
    @SidedProxy(clientSide="com.commoble.expandedmultiverse.client.CombinedClientProxy",
    		serverSide = "com.commoble.expandedmultiverse.server.DedicatedServerProxy")
    public static CommonProxy proxy;
    
    /**
     * Run before anything else; read the config, create blocks, items, etc, register w/ GameRegistry
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }
    
    /**
     * Setup anything that doesn't go in pre- or post-init. Build data structures, register recipes,
     * send FMLInterModComms messages to other mods
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.load(event);
    }
    
    /**
     * Handle interaction with other mods, complete setup base on this
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
    
    @EventHandler
    public void serverStarted(FMLServerStartedEvent event)
    {
    	proxy.serverStarted(event);
    }
    
    /**
     * Generates a string with the mod prefix from a base string to get the full string ID
     * e.g. "models/banana" -> "expandedmultiverse:models/banana"
     */
    public static String appendPrefix(String unprefixedString)
    {
		return ExpandedMultiverseMod.MODID + ":" + unprefixedString;
    }
}