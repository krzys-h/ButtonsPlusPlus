package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICraftingButtonRecipe {
	public class CraftingButtonComponents {
		public ItemStack base = null;
		public ItemStack click = null;
		public ItemStack label = null;
	}
	
	public CraftingButtonComponents getComponents(InventoryCrafting craftingTable);
}
