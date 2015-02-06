package pl.krzysh.minecraft.mod.buttonsplusplus.multipart;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pl.krzysh.minecraft.mod.buttonsplusplus.init.ModBlocks;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class RegisterButtonPart implements IPartFactory, IPartConverter
{
	@Override
	public TMultiPart createPart(String name, boolean client)
	{
		if(name.equals(Names.Blocks.BUTTON)) return new ButtonPart();
		
		return null;
	}
	
	public void init()
	{
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[]{
			Names.Blocks.BUTTON
		});
	}

	@Override
	public Iterable<Block> blockTypes() {
		List<Block> l = new ArrayList<Block>();
		l.add(ModBlocks.button);
		return l;
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos)
	{
		Block b = world.getBlock(pos.x, pos.y, pos.z);
		int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
		TileEntity tile = world.getTileEntity(pos.x, pos.y, pos.z);
		if(b == ModBlocks.button)
			return new ButtonPart((TileEntityButton)tile);
		
		return null;
	}
}
