package pl.krzysh.minecraft.mod.buttonsplusplus.util;

public enum MinecraftRainbow {
	WHITE(0xDDDDDD),
	ORANGE(0xDB7D3E),
	MAGENTA(0xB350BC),
	LIGHTBLUE(0x6B8AC9),
	YELLOW(0xB1A627),
	LIME(0x41AE38),
	PINK(0xD08499),
	GRAY(0x404040),
	LIGHTGRAY(0x9AA1A1),
	CYAN(0x2E6E89),
	PURPLE(0x7E3DB5),
	BLUE(0x2E388D),
	BROWN(0x4F321F),
	GREEN(0x35461B),
	RED(0x963430),
	BLACK(0x191616);

	public int color;
	private MinecraftRainbow(int color)
	{
		this.color = color;
	}
}
