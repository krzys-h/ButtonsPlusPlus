package pl.krzysh.minecraft.mod.buttonsplusplus.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.MinecraftRainbow;

public class TileEntityButton extends TileEntity {
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

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
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

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
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
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord,
				this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}
}
