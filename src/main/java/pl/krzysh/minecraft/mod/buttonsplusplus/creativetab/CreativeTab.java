package pl.krzysh.minecraft.mod.buttonsplusplus.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModBlocks;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;

public class CreativeTab {
	public static final CreativeTabs buttons = new CreativeTabs(Version.MODID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(ModBlocks.button);
		}
	};
}
