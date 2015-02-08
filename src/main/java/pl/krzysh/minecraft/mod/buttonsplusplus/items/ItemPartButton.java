package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.multipart.PartButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.TMultiPart;

public class ItemPartButton extends JItemMultiPart {
	public ItemPartButton() {
		super();
		setUnlocalizedName(Names.MultiParts.BUTTON);
		setCreativeTab(CreativeTab.buttons);
	}

	@Override
	public void registerIcons(IIconRegister iconreg) {

	}

	@Override
	public TMultiPart newPart(ItemStack stack, EntityPlayer player, World world, BlockCoord pos, int side, Vector3 vecHit) {
		return PartButton.placement(world, pos, side, stack);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
		//TODO: Do we really want 16*16*2*17 items in the creative tab?
		ItemStack stack;
		PartButton tile = new PartButton();

		tile.base = "cube";
		tile.click = "cube";
		tile.base_color = MinecraftRainbow.GRAY.color;
		tile.click_color = MinecraftRainbow.RED.color;

		stack = new ItemStack(item, 1);
		tile.click = "cube";
		tile.toItem(stack);
		list.add(stack);

		stack = new ItemStack(item, 1);
		tile.click = "round";
		tile.toItem(stack);
		list.add(stack);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		if(itemstack.stackTagCompound != null) {
			String text = itemstack.stackTagCompound.getString("text");
			String base = itemstack.stackTagCompound.getString("base");
			String click = itemstack.stackTagCompound.getString("click");
			if(!text.isEmpty()) {
				list.add("Text: " + text);
			}
			list.add("Base: " + base);
			list.add("Click: " + click);
		}
	}
}
