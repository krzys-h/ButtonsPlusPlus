package pl.krzysh.minecraft.mod.buttonsplusplus.util;

public class Color {
	protected int r, g, b;

	public Color(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color(int color) {
		this.fromInteger(color);
	}

	public Color() {
		this.r = 0;
		this.g = 0;
		this.b = 0;
	}

	public void fromInteger(int color) {
		this.r = (color >> 16) & 0xFF;
		this.g = (color >> 8) & 0xFF;
		this.b = color & 0xFF;
	}

	public int toInteger() {
		throw new RuntimeException("Not yet implemented");
	}
	
	public int getR() {
		return this.r;
	}
	
	public int getG() {
		return this.g;
	}
	
	public int getB() {
		return this.b;
	}
	
	public float getRf() {
		return getR()/255F;
	}
	
	public float getGf() {
		return getG()/255F;
	}
	
	public float getBf() {
		return getB()/255F;
	}
	
	public void setR(int r) {
		this.r = r;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setB(int b) {
		this.b = b;
	}
	
	public void set(int r, int g, int b) {
		setR(r);
		setG(g);
		setB(b);
	}
	
	public void setRf(float r) {
		this.r = Math.round(r*255F);
	}
	
	public void setGf(float g) {
		this.g = Math.round(g*255F);
	}
	
	public void setBf(float b) {
		this.b = Math.round(b*255F);
	}
	
	public void setf(float r, float g, float b) {
		setRf(r);
		setGf(g);
		setBf(b);
	}
}
