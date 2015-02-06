package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import pl.krzysh.minecraft.mod.buttonsplusplus.ButtonPartRegistry;
import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;

public class ItemButtonPart extends Item {
	public ItemButtonPart()
	{
		super();
		setCreativeTab(CreativeTab.buttons);
		setUnlocalizedName(Names.Items.BUTTON_PART);
	}
	
	public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
		for (String type : ButtonPartRegistry.instance.getTypes()) {
			for (String part : ButtonPartRegistry.instance.getParts(type)) {
				for (MinecraftRainbow color : MinecraftRainbow.values()) {
					ItemStack stack = new ItemStack(item, 1, 0);
					stack.stackTagCompound = new NBTTagCompound();
					stack.stackTagCompound.setString("type", type);
					stack.stackTagCompound.setString("part", part);
					stack.stackTagCompound.setInteger("color", color.color);
					list.add(stack);
				}
			}
		}
	}
}
