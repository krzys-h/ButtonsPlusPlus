package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingItems {
	//TODO: coloring
	public static void registerCrafting() {
		// Do not break my formatting, please!
		//@formatter:off
		
		//TODO: temporary recipe until I figure out something better
		GameRegistry.addSmelting(
			new ItemStack(Item.getItemFromBlock(Blocks.stonebrick)),
			new ItemStack(ModItems.button_building_material, 16), 0.0F
		);
		
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
		
		ItemStack lamp = new ItemStack(ModItems.button_lamp);
		lamp.stackTagCompound = new NBTTagCompound();
		lamp.stackTagCompound.setInteger("color", MinecraftRainbow.WHITE.color);
		GameRegistry.addRecipe(
			lamp,
			" L ",
			"###",
			'#', ModItems.button_building_material,
			'L', Item.getItemFromBlock(Blocks.redstone_lamp)
		);
		
		//@formatter:on
	}
}
