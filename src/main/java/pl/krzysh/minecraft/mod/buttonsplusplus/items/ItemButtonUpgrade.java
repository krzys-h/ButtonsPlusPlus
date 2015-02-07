package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import java.util.List;

import pl.krzysh.minecraft.mod.buttonsplusplus.ButtonPartRegistry;
import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ItemButtonUpgrade extends BaseItem {
	public ItemButtonUpgrade(boolean colorable) {
		super(colorable);
	}
	
	public abstract String getUpgradeCategory();
}
