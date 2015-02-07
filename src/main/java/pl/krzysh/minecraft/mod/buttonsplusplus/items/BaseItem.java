package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;

public class BaseItem extends Item {
	protected boolean colorable;
	
	public BaseItem(boolean colorable)
	{
		this.colorable = colorable;
	}
	
	public boolean getColorable()
	{
		return this.colorable;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
		if(this.colorable) {
			getColorSubItems(item, list, new NBTTagCompound());
		} else {
			list.add(new ItemStack(item));
		}
	}
	
	protected void getColorSubItems(Item item, List list, NBTTagCompound nbt) {
		for (MinecraftRainbow color : MinecraftRainbow.values()) {
			ItemStack stack = new ItemStack(item);
			stack.stackTagCompound = (NBTTagCompound)nbt.copy();
			stack.stackTagCompound.setInteger("color", color.color);
			list.add(stack);
		}
	}
}
