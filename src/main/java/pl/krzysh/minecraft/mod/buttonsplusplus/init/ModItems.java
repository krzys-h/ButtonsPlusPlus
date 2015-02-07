package pl.krzysh.minecraft.mod.buttonsplusplus.init;

import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonBuildingMaterial;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonLabel;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonPart;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonUpgradeAutoRelease;
import pl.krzysh.minecraft.mod.buttonsplusplus.items.ItemButtonUpgradeLamp;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Version.MODID)
public class ModItems {
	public static final ItemButtonBuildingMaterial button_building_material = new ItemButtonBuildingMaterial();
	public static final ItemButtonPart button_part = new ItemButtonPart();
	public static final ItemButtonLabel button_label = new ItemButtonLabel();
	public static final ItemButtonUpgradeLamp button_lamp = new ItemButtonUpgradeLamp();
	public static final ItemButtonUpgradeAutoRelease button_auto_release = new ItemButtonUpgradeAutoRelease();

	public static void init() {
		GameRegistry.registerItem(button_building_material, Names.Items.BUTTON_BUILDING_MATERIAL);
		GameRegistry.registerItem(button_part, Names.Items.BUTTON_PART);
		GameRegistry.registerItem(button_label, Names.Items.BUTTON_LABEL);
		GameRegistry.registerItem(button_lamp, Names.Items.BUTTON_LAMP);
		GameRegistry.registerItem(button_auto_release, Names.Items.BUTTON_AUTO_RELEASE);
	}
}
