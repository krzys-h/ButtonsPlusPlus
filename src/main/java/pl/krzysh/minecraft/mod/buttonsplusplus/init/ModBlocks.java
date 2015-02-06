package pl.krzysh.minecraft.mod.buttonsplusplus.init;

import net.minecraft.block.Block;
import pl.krzysh.minecraft.mod.buttonsplusplus.blocks.BlockButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemBlockButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Version.MODID)
public class ModBlocks {
	public static final BlockButton button = new BlockButton();
	
	public static void init()
	{
		GameRegistry.registerBlock(button, ItemBlockButton.class, Names.Blocks.BUTTON);
		GameRegistry.registerTileEntity(TileEntityButton.class, Names.Blocks.BUTTON);
	}
}
