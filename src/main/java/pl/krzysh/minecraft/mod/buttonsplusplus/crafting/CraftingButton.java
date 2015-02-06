package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModBlocks;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonLabel;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonPart;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class CraftingButton implements IRecipe {
	//TODO: This could be probably implemented a little cleaner
	@Override
	public boolean matches(InventoryCrafting craftingTable, World world) {
		boolean matched = false;
		ItemStack matchedClick = null;
		ItemStack matchedBase = null;
		ItemStack matchedLabel = null;
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				ItemStack click = craftingTable.getStackInSlot(y*3+x);
				if(click == null) continue;
				if(click.getItem() instanceof ItemButtonLabel) {
					if(matchedLabel != null) return false;
					matchedLabel = click;
					continue;
				}
				
				if(!matched) {
					if(!(click.getItem() instanceof ItemButtonPart)) return false;
					if(click.stackTagCompound == null) return false;
					if(click.stackTagCompound.getString("type") != Names.Items.ButtonPart.Types.CLICK) return false;
					
					if(y >= 2) continue;
					ItemStack base = craftingTable.getStackInSlot((y+1)*3+x);
					if(base == null) return false;
					if(!(base.getItem() instanceof ItemButtonPart)) return false;
					if(base.stackTagCompound == null) return false;
					if(base.stackTagCompound.getString("type") != Names.Items.ButtonPart.Types.BASE) return false;
					
					matchedClick = click;
					matchedBase = base;
					matched = true;
				} else {
					if(click == matchedClick) continue;
					if(click == matchedBase) continue;
					if(click == matchedLabel) continue;
					return false;
				}
			}
		}
		return matched;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftingTable) {
		boolean matched = false;
		ItemStack matchedClick = null;
		ItemStack matchedBase = null;
		ItemStack matchedLabel = null;
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				ItemStack click = craftingTable.getStackInSlot(y*3+x);
				if(click == null) continue;
				if(click.getItem() instanceof ItemButtonLabel) {
					if(matchedLabel != null) return null;
					matchedLabel = click;
					continue;
				}
				
				if(!matched) {
					if(!(click.getItem() instanceof ItemButtonPart)) return null;
					if(click.stackTagCompound == null) return null;
					if(click.stackTagCompound.getString("type") != Names.Items.ButtonPart.Types.CLICK) return null;
					
					if(y >= 2) continue;
					ItemStack base = craftingTable.getStackInSlot((y+1)*3+x);
					if(base == null) return null;
					if(!(base.getItem() instanceof ItemButtonPart)) return null;
					if(base.stackTagCompound == null) return null;
					if(base.stackTagCompound.getString("type") != Names.Items.ButtonPart.Types.BASE) return null;
					
					matchedClick = click;
					matchedBase = base;
					matched = true;
				} else {
					if(click == matchedClick) continue;
					if(click == matchedBase) continue;
					if(click == matchedLabel) continue;
					return null;
				}
			}
		}
		if(!matched) return null;
		
		ItemStack itemstack = new ItemStack(Item.getItemFromBlock(ModBlocks.button), 1);
		itemstack.stackTagCompound = new NBTTagCompound();
		itemstack.stackTagCompound.setString("base", matchedBase.stackTagCompound.getString("part"));
		itemstack.stackTagCompound.setString("click", matchedClick.stackTagCompound.getString("part"));
		itemstack.stackTagCompound.setInteger("base_color", matchedBase.stackTagCompound.getInteger("color"));
		itemstack.stackTagCompound.setInteger("click_color", matchedClick.stackTagCompound.getInteger("color"));
		if(matchedLabel != null && matchedLabel.hasDisplayName())
			itemstack.stackTagCompound.setString("text", matchedLabel.getDisplayName());
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
