package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemButtonLabel extends Item {
	public ItemButtonLabel() {
		super();
		setCreativeTab(CreativeTab.buttons);
		setUnlocalizedName(Names.Items.BUTTON_LABEL);
	}
	
	@Override
	public void registerIcons(IIconRegister iconreg)
	{
	}
}
