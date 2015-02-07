package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class ItemButtonUpgradeLamp extends ItemButtonUpgrade {
	public ItemButtonUpgradeLamp() {
		super(true);
		setCreativeTab(CreativeTab.buttons);
		setUnlocalizedName(Names.Items.BUTTON_LAMP);
	}

	@Override
	public String getUpgradeCategory() {
		return Names.Items.BUTTON_LAMP;
	}
}
