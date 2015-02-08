package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

//TODO: Temporary name
public class ItemButtonBuildingMaterial extends BaseItem {
	public ItemButtonBuildingMaterial() {
		super(false);
		setCreativeTab(CreativeTab.buttons);
		setUnlocalizedName(Names.Items.BUTTON_BUILDING_MATERIAL);
	}
}
