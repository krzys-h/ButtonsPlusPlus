package pl.krzysh.minecraft.mod.buttonsplusplus.util;

public class ColorUtils {
	private static float getOneColor(int color, int idx)
	{
		return ((color >> (16-idx*8)) & 0xFF)/255F;
	}
	
	public static float getR(int color)
	{
		return getOneColor(color, 0);
	}
	
	public static float getG(int color)
	{
		return getOneColor(color, 1);
	}
	
	public static float getB(int color)
	{
		return getOneColor(color, 2);
	}
}
