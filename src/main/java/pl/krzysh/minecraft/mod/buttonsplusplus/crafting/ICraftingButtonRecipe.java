package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public interface ICraftingButtonRecipe {
	public class CraftingButtonComponents {
		public ItemStack base = null;
		public ItemStack click = null;
		public ItemStack label = null;
		public Map<String, ItemStack> upgrades = new HashMap<String, ItemStack>();
	}

	public CraftingButtonComponents getComponents(InventoryCrafting craftingTable);
}
