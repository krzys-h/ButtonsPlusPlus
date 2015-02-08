package pl.krzysh.minecraft.mod.buttonsplusplus.items;


public abstract class ItemButtonUpgrade extends BaseItem {
	public ItemButtonUpgrade(boolean colorable) {
		super(colorable);
	}
	
	public abstract String getUpgradeCategory();
}
