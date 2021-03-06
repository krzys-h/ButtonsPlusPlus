package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class ItemButtonLabel extends BaseItem {
	public ItemButtonLabel() {
		super(false);
		setCreativeTab(CreativeTab.buttons);
		setUnlocalizedName(Names.Items.BUTTON_LABEL);
	}

	@Override
	public void registerIcons(IIconRegister iconreg) {
	}
}
