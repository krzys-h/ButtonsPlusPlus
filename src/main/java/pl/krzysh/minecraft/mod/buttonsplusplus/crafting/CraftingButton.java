package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class CraftingButton implements IRecipe {
	// We might need more recipes later
	ICraftingButtonRecipe recipe = new CraftingButtonRecipe();

	@Override
	public boolean matches(InventoryCrafting craftingTable, World world) {
		return recipe.getComponents(craftingTable) != null;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftingTable) {
		ICraftingButtonRecipe.CraftingButtonComponents components = recipe.getComponents(craftingTable);
		if(components == null)
			return null;

		ItemStack itemstack = new ItemStack(ModItems.button, 1);
		itemstack.stackTagCompound = new NBTTagCompound();
		itemstack.stackTagCompound.setString("base", components.base.stackTagCompound.getString("part"));
		itemstack.stackTagCompound.setString("click", components.click.stackTagCompound.getString("part"));
		itemstack.stackTagCompound.setInteger("base_color", components.base.stackTagCompound.getInteger("color"));
		itemstack.stackTagCompound.setInteger("click_color", components.click.stackTagCompound.getInteger("color"));
		if(components.label != null && components.label.hasDisplayName())
			itemstack.stackTagCompound.setString("text", components.label.getDisplayName());
		else
			itemstack.stackTagCompound.setString("text", "");
		if(components.upgrades.containsKey(Names.Items.BUTTON_LAMP)) {
			itemstack.stackTagCompound.setBoolean("lamp", true);
			itemstack.stackTagCompound.setInteger("lamp_color", components.upgrades.get(Names.Items.BUTTON_LAMP).stackTagCompound.getInteger("color"));
		} else {
			itemstack.stackTagCompound.setBoolean("lamp", false);
		}
		itemstack.stackTagCompound.setBoolean("autorelease", components.upgrades.containsKey(Names.Items.BUTTON_AUTO_RELEASE));
		return itemstack;
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}
}
