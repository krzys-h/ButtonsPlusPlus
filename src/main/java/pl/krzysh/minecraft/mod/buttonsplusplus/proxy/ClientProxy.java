package pl.krzysh.minecraft.mod.buttonsplusplus.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonLabelRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonPartRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ModelLibrary;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		ModelLibrary.init();

		MinecraftForgeClient.registerItemRenderer(ModItems.button, new ButtonRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.button_part, new ButtonPartRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.button_label, new ButtonLabelRenderer());
	}
}
