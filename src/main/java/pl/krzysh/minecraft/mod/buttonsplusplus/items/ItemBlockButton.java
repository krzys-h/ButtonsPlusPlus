package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import codechicken.lib.vec.BlockCoord;

public class ItemBlockButton extends ItemBlock {
	public ItemBlockButton(Block block) {
		super(block);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if(stack.stackTagCompound == null) return false;
		
		boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		
    	TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
    	if(tileentity == null) return ret;

        tileentity.orientation = ForgeDirection.getOrientation(side).getOpposite();
        tileentity.fromItem(stack);
        
		return ret;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        if (itemstack.stackTagCompound != null) {
        	String text = itemstack.stackTagCompound.getString("text");
        	String base = itemstack.stackTagCompound.getString("base");
        	String click = itemstack.stackTagCompound.getString("click");
        	if(!text.isEmpty()) {
        		list.add("Text: "+text);
        	}
        	list.add("Base: "+base);
        	list.add("Click: "+click);
        }
	}
}
