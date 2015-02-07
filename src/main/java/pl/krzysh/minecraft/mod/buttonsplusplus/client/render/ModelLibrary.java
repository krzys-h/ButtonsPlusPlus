package pl.krzysh.minecraft.mod.buttonsplusplus.client.render;

import java.util.HashMap;
import java.util.Map;

import pl.krzysh.minecraft.mod.buttonsplusplus.ButtonPartRegistry;
import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelLibrary {
	public static Map<String, Map<String, IModelCustom>> models = null;

	public static void init() {
		models = new HashMap<String, Map<String, IModelCustom>>();
		for(String type : ButtonPartRegistry.instance.getTypes()) {
			Map<String, IModelCustom> typeMap = new HashMap<String, IModelCustom>();
			models.put(type, typeMap);
			for(String part : ButtonPartRegistry.instance.getParts(type)) {
				typeMap.put(part, AdvancedModelLoader.loadModel(new ResourceLocation(Version.MODID.toLowerCase(), "models/" + type + "_" + part + ".obj")));
			}
		}
	}

	public static IModelCustom getModel(String type, String part) {
		if(models == null)
			return null;
		if(models.get(type) == null)
			return null;
		return models.get(type).get(part);
	}
}
