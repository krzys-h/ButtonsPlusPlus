package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonPart;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingItems {
	public static void registerCrafting()
	{
		GameRegistry.addRecipe(
			ModItems.button_part.getItemStack(Names.Items.ButtonPart.Types.BASE, Names.Items.ButtonPart.Shapes.CUBE),
			"###",
			"# #",
			"###",
			'#', ModItems.button_building_material
		);

		GameRegistry.addRecipe(
			ModItems.button_part.getItemStack(Names.Items.ButtonPart.Types.CLICK, Names.Items.ButtonPart.Shapes.CUBE),
			"###",
			"#B#",
			"###",
			'#', ModItems.button_building_material,
			'B', Item.getItemFromBlock(Blocks.stone_button)
		);

		GameRegistry.addRecipe(
			ModItems.button_part.getItemStack(Names.Items.ButtonPart.Types.CLICK, Names.Items.ButtonPart.Shapes.ROUND),
			" # ",
			"#B#",
			" # ",
			'#', ModItems.button_building_material,
			'B', Item.getItemFromBlock(Blocks.stone_button)
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(ModItems.button_label, 3),
			new ItemStack(ModItems.button_building_material),
			new ItemStack(Items.paper),
			new ItemStack(Items.paper),
			new ItemStack(Items.paper)
		);
	}
}
