package pl.krzysh.minecraft.mod.buttonsplusplus.init;

import pl.krzysh.minecraft.mod.buttonsplusplus.multipart.PartButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class ModParts implements IPartFactory {
	public static ModParts instance = null;

	public static void init() {
		if(instance != null)
			throw new RuntimeException("ModParts.init called twice!");
		instance = new ModParts();
		MultiPartRegistry.registerParts(instance, new String[] { Names.MultiParts.BUTTON });
	}

	@Override
	public TMultiPart createPart(String name, boolean client) {
		if(name.equals(Names.MultiParts.BUTTON))
			return new PartButton();

		return null;
	}
}
