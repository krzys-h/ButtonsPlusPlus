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

public abstract class ItemButtonUpgrade extends Item {
	protected boolean colorable;
	
	public ItemButtonUpgrade(boolean colorable) {
		super();
		this.colorable = colorable;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
		if(this.colorable) {
			for (MinecraftRainbow color : MinecraftRainbow.values()) {
				ItemStack stack = new ItemStack(item);
				stack.stackTagCompound = new NBTTagCompound();
				stack.stackTagCompound.setInteger("color", color.color);
				list.add(stack);
			}
		} else {
			list.add(new ItemStack(item));
		}
	}
	
	public abstract String getUpgradeCategory();
}
