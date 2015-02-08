package pl.krzysh.minecraft.mod.buttonsplusplus.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonLabel;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonPart;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonUpgrade;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class CraftingButtonRecipe implements ICraftingButtonRecipe {
	@Override
	public CraftingButtonComponents getComponents(InventoryCrafting craftingTable) {
		boolean matched = false;
		CraftingButtonComponents components = new CraftingButtonComponents();
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				ItemStack click = craftingTable.getStackInSlot(y * 3 + x);
				if(click == null)
					continue;
				if(click.getItem() instanceof ItemButtonLabel) {
					if(components.label != null)
						return null;
					if(!click.hasDisplayName())
						return null;
					components.label = click;
					continue;
				}
				if(click.getItem() instanceof ItemButtonUpgrade) {
					String upgradeType = ((ItemButtonUpgrade) click.getItem()).getUpgradeCategory();
					if(components.upgrades.containsKey(upgradeType))
						return null;
					components.upgrades.put(upgradeType, click);
					continue;
				}

				if(!matched) {
					if(!(click.getItem() instanceof ItemButtonPart))
						return null;
					if(click.stackTagCompound == null)
						return null;
					if(!click.stackTagCompound.getString("type").equals(Names.Items.ButtonPart.Types.CLICK))
						return null;

					if(y >= 2)
						continue;
					ItemStack base = craftingTable.getStackInSlot((y + 1) * 3 + x);
					if(base == null)
						return null;
					if(!(base.getItem() instanceof ItemButtonPart))
						return null;
					if(base.stackTagCompound == null)
						return null;
					if(!base.stackTagCompound.getString("type").equals(Names.Items.ButtonPart.Types.BASE))
						return null;

					components.click = click;
					components.base = base;
					matched = true;
				} else {
					if(click == components.click)
						continue;
					if(click == components.base)
						continue;
					return null;
				}
			}
		}
		if(!matched)
			return null;

		return components;
	}
}
