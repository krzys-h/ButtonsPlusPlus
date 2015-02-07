package pl.krzysh.minecraft.mod.buttonsplusplus.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonLabelRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonPartRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ModelLibrary;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModBlocks;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		ModelLibrary.init();

		ButtonRenderer buttonRenderer = new ButtonRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityButton.class, buttonRenderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.button), buttonRenderer);

		MinecraftForgeClient.registerItemRenderer(ModItems.button_part, new ButtonPartRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.button_label, new ButtonLabelRenderer());
	}
}
