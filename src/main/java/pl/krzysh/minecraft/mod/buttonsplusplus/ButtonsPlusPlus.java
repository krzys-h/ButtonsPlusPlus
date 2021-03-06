package pl.krzysh.minecraft.mod.buttonsplusplus;

import pl.krzysh.minecraft.mod.buttonsplusplus.crafting.Crafting;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModParts;
import pl.krzysh.minecraft.mod.buttonsplusplus.integration.IntegrationComputerCraft;
import pl.krzysh.minecraft.mod.buttonsplusplus.proxy.IProxy;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Version.MODID, version = Version.VERSION, dependencies = Version.DEPENDENCIES)
public class ButtonsPlusPlus {
	@Instance(value = Version.MODID)
	public static ButtonsPlusPlus instance;

	@SidedProxy(clientSide = Version.PROXY_CLIENT, serverSide = Version.PROXY_COMMON)
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//ModBlocks.init();
		ModItems.init();
		ModParts.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		Crafting.init();

		if(Loader.isModLoaded("ComputerCraft"))
			IntegrationComputerCraft.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
