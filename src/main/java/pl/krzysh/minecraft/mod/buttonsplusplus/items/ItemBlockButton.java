package pl.krzysh.minecraft.mod.buttonsplusplus.items;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;

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

        ForgeDirection dir = ForgeDirection.getOrientation(side);

        if (dir == NORTH && world.isSideSolid(x, y, z + 1, NORTH))
        {
            tileentity.orientation = SOUTH;
        }
        else if (dir == SOUTH && world.isSideSolid(x, y, z - 1, SOUTH))
        {
            tileentity.orientation = NORTH;
        }
        else if (dir == WEST && world.isSideSolid(x + 1, y, z, WEST))
        {
            tileentity.orientation = EAST;
        }
        else if (dir == EAST && world.isSideSolid(x - 1, y, z, EAST))
        {
            tileentity.orientation = WEST;
        }
        
        tileentity.text = stack.stackTagCompound.getString("text");
        tileentity.base = stack.stackTagCompound.getString("base");
        tileentity.click = stack.stackTagCompound.getString("click");
        tileentity.base_color = stack.stackTagCompound.getInteger("base_color");
        tileentity.click_color = stack.stackTagCompound.getInteger("click_color");
        
		return ret;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        if (itemstack.stackTagCompound != null) {
        	String text = itemstack.stackTagCompound.hasKey("text") ? itemstack.stackTagCompound.getString("text") : null;
        	String base = itemstack.stackTagCompound.getString("base");
        	String click = itemstack.stackTagCompound.getString("click");
        	if(text != null) {
        		list.add("Text: "+text);
        	}
        	list.add("Base: "+base);
        	list.add("Click: "+click);
        }
	}
}
