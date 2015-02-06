package pl.krzysh.minecraft.mod.buttonsplusplus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.RecipeSorter;
import pl.krzysh.minecraft.mod.buttonsplusplus.crafting.CraftingButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.handlers.ButtonPlaceHandler;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModBlocks;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.multipart.RegisterButtonPart;
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
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Version.MODID, version = Version.VERSION, dependencies = Version.DEPENDENCIES)
public class ButtonsPlusPlus {
	@Instance(value = Version.MODID)
	public static ButtonsPlusPlus instance;

	@SidedProxy(clientSide = Version.PROXY_CLIENT, serverSide = Version.PROXY_COMMON)
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ButtonPlaceHandler());
		ModBlocks.init();
		ModItems.init();

		if (Loader.isModLoaded("ForgeMultipart"))
			new RegisterButtonPart().init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		GameRegistry.addRecipe(new CraftingButton()); // TODO: put this somewhere
		RecipeSorter.register(Version.MODID.toLowerCase() + ":gun", CraftingButton.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
