package pl.krzysh.minecraft.mod.buttonsplusplus.multipart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.ButtonRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.client.render.IPartRenderer;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModItems;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;
import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.IFaceRedstonePart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.McBlockPart;
import codechicken.multipart.minecraft.McSidedMetaPart;

public class PartButton extends McSidedMetaPart implements IFaceRedstonePart {
	public boolean active = false;
	public ForgeDirection orientation = ForgeDirection.NORTH;
	public float size = 0.5f;
	public int click_color = MinecraftRainbow.RED.color;
	public int base_color = MinecraftRainbow.GRAY.color;
	public String base = "cube";
	public String click = "round";
	public String text = "";
	public boolean lamp = false;
	public int lamp_color = MinecraftRainbow.RED.color;
	public boolean autorelease = false;
	
	IPartRenderer renderer = new ButtonRenderer();

	public PartButton() {
		super();
	}

	//TODO: is it needed?
	private PartButton(int meta) {
		super(meta);
	}

	public PartButton(ForgeDirection orientation, ItemStack item) {
		super(0);
		this.orientation = orientation;
		this.fromItem(item);
	}
	
	public void writeToItemNBT(NBTTagCompound tag) {
		tag.setInteger("click_color", this.click_color);
		tag.setInteger("base_color", this.base_color);
		tag.setString("base", this.base);
		tag.setString("click", this.click);
		tag.setString("text", this.text);
		tag.setBoolean("lamp", this.lamp);
		tag.setInteger("lamp_color", this.lamp_color);
		tag.setBoolean("autorelease", this.autorelease);
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("active", this.active);
		tag.setInteger("orientation", this.orientation.ordinal());
		tag.setFloat("size", this.size); //TODO: move to item
		this.writeToItemNBT(tag);
	}
	
	public void readFromItemNBT(NBTTagCompound tag) {
		this.click_color = tag.getInteger("click_color");
		this.base_color = tag.getInteger("base_color");
		this.base = tag.getString("base");
		this.click = tag.getString("click");
		this.text = tag.getString("text");
		this.lamp = tag.getBoolean("lamp");
		this.lamp_color = tag.getInteger("lamp_color");
		this.autorelease = tag.getBoolean("autorelease");
	}

	public void readFromNBT(NBTTagCompound tag) {
		this.active = tag.getBoolean("active");
		this.orientation = ForgeDirection.getOrientation(tag.getInteger("orientation"));
		this.size = tag.getFloat("size"); //TODO: move to item
		this.readFromItemNBT(tag);
	}

	public void fromItem(ItemStack item) {
		if (item.stackTagCompound == null)
			return;
		this.readFromItemNBT(item.stackTagCompound);
	}

	public void toItem(ItemStack item) {
		if (item.stackTagCompound == null)
			item.stackTagCompound = new NBTTagCompound();
		this.writeToItemNBT(item.stackTagCompound);
	}

	@Override
	public int sideForMeta(int meta) {
		return this.orientation.ordinal();
	}

	@Override
	public Block getBlock() {
		return null;
	}

	@Override
	public String getType() {
		return Names.MultiParts.BUTTON;
	}

	public Cuboid6 getBlockBounds(boolean dynamic) {
		float side_margin = (1 - this.size) / 2;
		float front = 0.175F;
		if(!this.active || !dynamic) {
			front += 0.125F;
		}
		front *= this.size;

		if(this.orientation == ForgeDirection.WEST) {
			return new Cuboid6(0F, side_margin, side_margin, front, 1F - side_margin, 1F - side_margin);
		}
		if(this.orientation == ForgeDirection.EAST) {
			return new Cuboid6(1F - front, side_margin, side_margin, 1F, 1F - side_margin, 1F - side_margin);
		}
		if(this.orientation == ForgeDirection.NORTH) {
			return new Cuboid6(side_margin, side_margin, 0F, 1F - side_margin, 1F - side_margin, front);
		}
		if(this.orientation == ForgeDirection.SOUTH) {
			return new Cuboid6(side_margin, side_margin, 1F - front, 1F - side_margin, 1F - side_margin, 1F);
		}

		return null;
	}

	@Override
	public Cuboid6 getBounds() {
		return getBlockBounds(true);
	}

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		List<Cuboid6> l = new ArrayList<Cuboid6>();
		l.add(getBlockBounds(false));
		return l;
	}

	public static McBlockPart placement(World world, BlockCoord pos, int side, ItemStack stack) {
		if(side == 0 || side == 1)
			return null;

		if(stack.stackTagCompound == null)
			return null;

		pos = pos.copy().offset(side ^ 1);
		if(!world.isSideSolid(pos.x, pos.y, pos.z, ForgeDirection.getOrientation(side)))
			return null;

		return new PartButton(ForgeDirection.getOrientation(side).getOpposite(), stack);
	}

	@Override
	public boolean activate(EntityPlayer player, MovingObjectPosition part, ItemStack item) {
		if(!world().isRemote)
			toggle();

		return true;
	}

	private void toggle() {
		if(!this.active || !this.autorelease) {
			this.active = !this.active;
			world().playSoundEffect(x() + 0.5, y() + 0.5, z() + 0.5, "random.click", 0.3F, this.active ? 0.6F : 0.5F);

			sendDescUpdate();
			tile().notifyPartChange(this);
			tile().notifyNeighborChange(this.orientation.ordinal());
			tile().markDirty();
			if(this.active && this.autorelease) {
				scheduleTick(20); //TODO: make time customizable via upgrades
			}
		}
	}

	@Override
	public void onRemoved() {
		if(this.active)
			tile().notifyNeighborChange(this.orientation.ordinal());
	}

	@Override
	public int weakPowerLevel(int side) {
		return this.active ? 15 : 0;
	}

	@Override
	public int strongPowerLevel(int side) {
		return this.active && ForgeDirection.getOrientation(side) == this.orientation ? 15 : 0;
	}

	@Override
	public boolean canConnectRedstone(int side) {
		return true;
	}

	@Override
	public int getFace() {
		return this.orientation.ordinal();
	}

	@Override
	public void save(NBTTagCompound tag) {
		this.writeToNBT(tag);
	}

	@Override
	public void load(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public void writeDesc(MCDataOutput packet) {
		NBTTagCompound tag = new NBTTagCompound();
		this.save(tag);
		packet.writeNBTTagCompound(tag);
	}

	@Override
	public void readDesc(MCDataInput packet) {
		NBTTagCompound tag = new NBTTagCompound();
		tag = packet.readNBTTagCompound();
		this.load(tag);
	}
	
	@Override
	public boolean renderStatic(Vector3 pos, int pass) {
		return false;
	}

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		renderer.renderPartAt(this, pos.x, pos.y, pos.z);
	}

	@Override
	public int getLightValue() {
		return this.lamp && this.active ? 7 : 0;
	}

	@Override
	public ItemStack pickItem(MovingObjectPosition hit) {
		ItemStack stack = new ItemStack(ModItems.button);
		this.toItem(stack);
		return stack;
	}

	@Override
	public Iterable<ItemStack> getDrops() {
		ItemStack stack = new ItemStack(ModItems.button);
		this.toItem(stack);
		return Arrays.asList(stack);
	}

	// TODO: Why McSidedMetaPart doesn't do it like this?
	@Override
	public void drop() {
		for(ItemStack stack : getDrops()) {
			TileMultipart.dropItem(stack, world(), Vector3.fromTileEntityCenter(tile()));
		}
		tile().remPart(this);
	}

	@Override
	public void scheduledTick() {
		if(this.active && this.autorelease) {
			this.active = false;
			world().playSoundEffect(x() + 0.5, y() + 0.5, z() + 0.5, "random.click", 0.3F, 0.5F);

			sendDescUpdate();
			tile().notifyPartChange(this);
			tile().notifyNeighborChange(this.orientation.ordinal());
			tile().markDirty();
		}
	}
	
	@Override
	public IIcon getBreakingIcon(Object subPart, int side)
	{
		return null; //TODO
	}
	
	@Override
	public IIcon getBrokenIcon(int side)
	{
		return null; //TODO
	}
}
