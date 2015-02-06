package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraftforge.oredict.RecipeSorter;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import cpw.mods.fml.common.registry.GameRegistry;

public class Crafting {
	public static void init()
	{
		// General crafting (normal shaped/shapeless recipes)
		CraftingItems.registerCrafting();
		
		// Special button crafting recipe
		GameRegistry.addRecipe(new CraftingButton());
		RecipeSorter.register(Version.MODID.toLowerCase() + ":button", CraftingButton.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
	}
}
