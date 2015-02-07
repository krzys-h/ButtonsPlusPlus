package pl.krzysh.minecraft.mod.buttonsplusplus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Names;

public class ButtonPartRegistry {
	public static ButtonPartRegistry instance = new ButtonPartRegistry();

	protected Map<String, Set<String>> list;

	private ButtonPartRegistry() {
		list = new HashMap<String, Set<String>>();
		registerAll();
	}

	private void registerAll() {
		addType(Names.Items.ButtonPart.Types.BASE);
		addPart(Names.Items.ButtonPart.Types.BASE, Names.Items.ButtonPart.Shapes.CUBE);

		addType(Names.Items.ButtonPart.Types.CLICK);
		addPart(Names.Items.ButtonPart.Types.CLICK, Names.Items.ButtonPart.Shapes.CUBE);
		addPart(Names.Items.ButtonPart.Types.CLICK, Names.Items.ButtonPart.Shapes.ROUND);
	}

	public void addType(String type) {
		if(list.get(type) != null)
			throw new RuntimeException("Type " + type + " registered twice");
		list.put(type, new HashSet<String>());
	}

	public void addPart(String type, String part) {
		Set<String> l = list.get(type);
		if(l == null)
			throw new RuntimeException("Type " + type + " not registered");
		if(l.contains(part))
			throw new RuntimeException("Part " + part + " already registered");
		l.add(part);
	}

	public Set<String> getTypes() {
		return list.keySet();
	}

	public Set<String> getParts(String type) {
		return list.get(type);
	}
}
