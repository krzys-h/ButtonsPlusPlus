package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class ItemButtonUpgradeAutoRelease extends ItemButtonUpgrade {
	public ItemButtonUpgradeAutoRelease() {
		super(false);
		setCreativeTab(CreativeTab.buttons);
		setUnlocalizedName(Names.Items.BUTTON_AUTO_RELEASE);
	}

	@Override
	public String getUpgradeCategory() {
		return Names.Items.BUTTON_AUTO_RELEASE;
	}
}