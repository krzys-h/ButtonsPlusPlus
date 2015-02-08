package pl.krzysh.minecraft.mod.buttonsplusplus.integration;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pl.krzysh.minecraft.mod.buttonsplusplus.multipart.PartButton;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

@Optional.Interface(iface = "dan200.computercraft.api.peripheral.IPeripheralProvider", modid = "ComputerCraft")
public class IntegrationComputerCraft implements IPeripheralProvider {
	public static IntegrationComputerCraft instance = null;

	public static void init() {
		instance = new IntegrationComputerCraft();
		ComputerCraftAPI.registerPeripheralProvider(instance);
	}

	@Optional.Method(modid = "ComputerCraft")
	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(!(te instanceof TileMultipart))
			return null;
		TileMultipart multipart = (TileMultipart) te;
		for(TMultiPart thispart : multipart.jPartList()) {
			if(!(thispart instanceof PartButton))
				continue;
			PartButton part = (PartButton) thispart;
			if(part.orientation.ordinal() == side) {
				return (IPeripheral) part;
			}
		}

		return null;
	}
}
