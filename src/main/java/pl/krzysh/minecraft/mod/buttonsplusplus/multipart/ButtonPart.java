package pl.krzysh.minecraft.mod.buttonsplusplus.multipart;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModBlocks;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.IFaceRedstonePart;
import codechicken.multipart.minecraft.McBlockPart;
import codechicken.multipart.minecraft.McSidedMetaPart;

public class ButtonPart extends McSidedMetaPart implements IFaceRedstonePart
{
	private TileEntityButton tile;
    
    public ButtonPart()
    {
    	super();
    	tile = new TileEntityButton();
    }
    
    //TODO: is it needed?
    private ButtonPart(int meta)
    {
        super(meta);
    	tile = new TileEntityButton();
    }
    
    public ButtonPart(ForgeDirection orientation, NBTTagCompound tag) {
		super(0);
		tile = new TileEntityButton();
		tile.orientation = orientation;
		tile.text = tag.getString("text");
		tile.base = tag.getString("base");
		tile.click = tag.getString("click");
		tile.base_color = tag.getInteger("base_color");
		tile.click_color = tag.getInteger("click_color");
	}

	@Override
    public int sideForMeta(int meta)
    {
        return tile.orientation.ordinal();
    }

    @Override
    public Block getBlock()
    {
        return ModBlocks.button;
    }
    
    @Override
    public String getType()
    {
        return Names.Blocks.BUTTON;
    }
    
    @Override
    public Cuboid6 getBounds()
    {
        return ModBlocks.button.getBlockBounds(tile, true);
    }
    
    @Override
    public Iterable<Cuboid6> getOcclusionBoxes()
    {
    	List<Cuboid6> l = new ArrayList<Cuboid6>();
    	l.add(ModBlocks.button.getBlockBounds(tile, false));
    	return l;
    }

    public static McBlockPart placement(World world, BlockCoord pos, int side, ItemStack held)
    {
        if(side == 0 || side == 1)
            return null;
        
        if(held.stackTagCompound == null)
        	return null;
        
        pos = pos.copy().offset(side^1);
        if(!world.isSideSolid(pos.x, pos.y, pos.z, ForgeDirection.getOrientation(side)))
            return null;
        
        //TODO: why opposite?
        return new ButtonPart(ForgeDirection.getOrientation(side).getOpposite(), held.stackTagCompound);
    }

    @Override
    public boolean activate(EntityPlayer player, MovingObjectPosition part, ItemStack item)
    {
        if(!world().isRemote)
            toggle();
        
        return true;
    }

    private void toggle()
    {
        tile.active = !tile.active;
        world().playSoundEffect(x() + 0.5, y() + 0.5, z() + 0.5, "random.click", 0.3F, tile.active ? 0.6F : 0.5F);
        
        sendDescUpdate();
        tile().notifyPartChange(this);
        tile().notifyNeighborChange(tile.orientation.ordinal());
        tile().markDirty();
    }
    
    @Override
    public void onRemoved()
    {
        if(tile.active)
            tile().notifyNeighborChange(tile.orientation.ordinal());
    }
    
    @Override
    public int weakPowerLevel(int side)
    {
        return tile.active ? 15 : 0;
    }

    @Override
    public int strongPowerLevel(int side)
    {
        return tile.active && ForgeDirection.getOrientation(side) == tile.orientation ? 15 : 0;
    }

    @Override
    public boolean canConnectRedstone(int side)
    {
        return true;
    }
    
    @Override
    public int getFace() {
        return tile.orientation.ordinal();
    }
    
    @Override
    public void invalidateConvertedTile()
    {
    	tile = (TileEntityButton)this.world().getTileEntity(x(), y(), z());
    }
    
    @Override
    public void save(NBTTagCompound tag)
    {
    	tile.writeToNBT(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag)
    {
    	tile.readFromNBT(tag);
    }
    
    @Override
    public void writeDesc(MCDataOutput packet)
    {
    	NBTTagCompound tag = new NBTTagCompound();
    	this.save(tag);
    	packet.writeNBTTagCompound(tag);
    }

    @Override
    public void readDesc(MCDataInput packet)
    {
    	NBTTagCompound tag = new NBTTagCompound();
    	tag = packet.readNBTTagCompound();
    	this.load(tag);
    }
    
    @Override
    public void renderDynamic(Vector3 pos, float frame, int pass)
    {
    	TileEntitySpecialRenderer rend = TileEntityRendererDispatcher.instance.getSpecialRenderer(tile);
    	rend.func_147497_a(TileEntityRendererDispatcher.instance);
    	rend.renderTileEntityAt(tile, pos.x, pos.y, pos.z, 0);
    }
    
    @Override
    public int getLightValue()
    {
    	return tile.active ? 7 : 0; //TODO: for whaever reason this is buggy
    }
}