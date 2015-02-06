package pl.krzysh.minecraft.mod.buttonsplusplus.init;

import net.minecraft.item.Item;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonLabel;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonPart;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Version.MODID)
public class ModItems {
	public static final ItemButtonPart button_part = new ItemButtonPart();
	public static final ItemButtonLabel button_label = new ItemButtonLabel();
	
    public static void init()
    {
    	GameRegistry.registerItem(button_part, Names.Items.BUTTON_PART);
    	GameRegistry.registerItem(button_label, Names.Items.BUTTON_LABEL);
    }
}
