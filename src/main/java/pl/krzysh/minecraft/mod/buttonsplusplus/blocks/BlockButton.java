package pl.krzysh.minecraft.mod.buttonsplusplus.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.krzysh.minecraft.mod.buttonsplusplus.creativetab.CreativeTab;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.PartMetaAccess;

public class BlockButton extends Block implements ITileEntityProvider {
	public BlockButton() {
		super(Material.circuits);
		setBlockName(Names.Blocks.BUTTON);
		setCreativeTab(CreativeTab.buttons);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		return (dir == NORTH && world.isSideSolid(x, y, z + 1, NORTH)) ||
			   (dir == SOUTH && world.isSideSolid(x, y, z - 1, SOUTH)) ||
			   (dir == WEST  && world.isSideSolid(x + 1, y, z, WEST)) ||
			   (dir == EAST  && world.isSideSolid(x - 1, y, z, EAST));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return (world.isSideSolid(x - 1, y, z, EAST)) ||
			   (world.isSideSolid(x + 1, y, z, WEST)) ||
			   (world.isSideSolid(x, y, z - 1, SOUTH)) ||
			   (world.isSideSolid(x, y, z + 1, NORTH));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
	{
		if (this.shouldButtonNotFall(world, x, y, z))
		{
			TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
			if(tileentity == null) return;
			
			boolean flag = false;

			if (!world.isSideSolid(x - 1, y, z, EAST) && tileentity.orientation == WEST)
			{
				flag = true;
			}

			if (!world.isSideSolid(x + 1, y, z, WEST) && tileentity.orientation == EAST)
			{
				flag = true;
			}

			if (!world.isSideSolid(x, y, z - 1, SOUTH) && tileentity.orientation == NORTH)
			{
				flag = true;
			}

			if (!world.isSideSolid(x, y, z + 1, NORTH) && tileentity.orientation == SOUTH)
			{
				flag = true;
			}

			if (flag)
			{
				this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	private boolean shouldButtonNotFall(World world, int x, int y, int z)
	{
		if (!this.canPlaceBlockAt(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if(world instanceof PartMetaAccess) return;
		TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
		if(tileentity == null) return;
		this.updateBlockBounds(tileentity);
	}

	private void updateBlockBounds(TileEntityButton tile)
	{
		Cuboid6 bounds = getBlockBounds(tile, true);
		bounds.setBlockBounds(this);
	}
	
	public Cuboid6 getBlockBounds(TileEntityButton tile, boolean dynamic)
	{
		float side_margin = (1-tile.size)/2;
		float front = 0.175F;
		if(!tile.active || !dynamic) {
			front += 0.125F;
		}
		front *= tile.size;
		
		if(tile.orientation == WEST) {
			return new Cuboid6(0F, side_margin, side_margin, front, 1F-side_margin, 1F-side_margin);
		}
		if(tile.orientation == EAST) {
			return new Cuboid6(1F-front, side_margin, side_margin, 1F, 1F-side_margin, 1F-side_margin);
		}
		if(tile.orientation == NORTH) {
			return new Cuboid6(side_margin, side_margin, 0F, 1F-side_margin, 1F-side_margin, front);
		}
		if(tile.orientation == SOUTH) {
			return new Cuboid6(side_margin, side_margin, 1F-front, 1F-side_margin, 1F-side_margin, 1F);
		}
		
		return null;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float hitX, float hitY, float hitZ)
	{
		TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
		if(tileentity == null) return true;

		tileentity.active = !tileentity.active;
		if (tileentity.active)
		{
			world.markBlockForUpdate(x, y, z);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, 0.6F);
			this.updateNeighbors(world, x, y, z, tileentity.orientation);
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			return true;
		}
		else
		{
			world.markBlockForUpdate(x, y, z);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, 0.5F);
			this.updateNeighbors(world, x, y, z, tileentity.orientation);
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			return true;
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
		if(tileentity != null) {
			if (tileentity.active)
			{
				this.updateNeighbors(world, x, y, z, tileentity.orientation);
			}
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
	{
		TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
		if(tileentity == null) return 0;
		
		return tileentity.active ? 15 : 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side)
	{
		TileEntityButton tileentity = (TileEntityButton) world.getTileEntity(x, y, z);
		if(tileentity == null) return 0;

		if (!tileentity.active)
		{
			return 0;
		}
		else
		{
			if(tileentity.orientation == ForgeDirection.getOrientation(side).getOpposite()) return 15;
			return 0;
		}
	}

	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	private void updateNeighbors(World world, int x, int y, int z, ForgeDirection orientation)
	{
		world.notifyBlocksOfNeighborChange(x, y, z, this);

		if (orientation == WEST)
		{
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
		}
		else if (orientation == EAST)
		{
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
		}
		else if (orientation == NORTH)
		{
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
		}
		else if (orientation == SOUTH)
		{
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
		}
		else
		{
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
	 	return new TileEntityButton(); 
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list)
	{
		//TODO: Do we really want 16*16*2 items in the creative tab?
		ItemStack stack;
		
		stack = new ItemStack(item, 1);
		stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setString("base", "cube");
		stack.stackTagCompound.setString("click", "cube");
		stack.stackTagCompound.setInteger("base_color", MinecraftRainbow.GRAY.color);
		stack.stackTagCompound.setInteger("click_color", MinecraftRainbow.RED.color);
		list.add(stack);
		
		stack = new ItemStack(item, 1);
		stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setString("base", "cube");
		stack.stackTagCompound.setString("click", "round");
		stack.stackTagCompound.setInteger("base_color", MinecraftRainbow.GRAY.color);
		stack.stackTagCompound.setInteger("click_color", MinecraftRainbow.RED.color);
		list.add(stack);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if (block != this)
		{
			return block.getLightValue(world, x, y, z);
		}
		
		if(world instanceof PartMetaAccess) return 0; //meh
		TileEntityButton tile = (TileEntityButton)world.getTileEntity(x, y, z);
		
		return tile.active ? 7 : 0;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconreg)
	{
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
	{
		TileEntityButton tile = (TileEntityButton)world.getTileEntity(x, y, z);
		if(tile == null) return null;
		
		ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
		tile.toItem(stack);
		return stack;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		TileEntityButton tile = (TileEntityButton)world.getTileEntity(x, y, z);
		if(tile == null) return ret;
		
		ItemStack stack = new ItemStack(this);
		tile.toItem(stack);
		ret.add(stack);
		
		return ret;
	}
}
