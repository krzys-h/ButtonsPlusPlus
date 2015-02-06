package pl.krzysh.minecraft.mod.buttonsplusplus.client.render;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import pl.krzysh.minecraft.mod.buttonsplusplus.reference.Version;
import pl.krzysh.minecraft.mod.buttonsplusplus.tileentity.TileEntityButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.ColorUtils;

public class ButtonRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float f) {
		TileEntityButton tile = (TileEntityButton) tileEntity;
		
		GL11.glPushMatrix();
		scaleTranslateRotate(x, y, z, tile.orientation);
		renderButton(tile);
		GL11.glPopMatrix();
	}
	
	private void renderButton(TileEntityButton tile)
	{
		GL11.glTranslatef((1-tile.size)/2, 0F, (1-tile.size)/2);
		GL11.glScalef(tile.size, tile.size, tile.size);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glColor3f(ColorUtils.getR(tile.base_color), ColorUtils.getG(tile.base_color), ColorUtils.getB(tile.base_color));
		IModelCustom base_model = ModelLibrary.getModel("base", tile.base);
		if(base_model != null)
			base_model.renderAll();

		float colormod = 0F;
		if(tile.active) {
			GL11.glTranslatef(0F, -0.125F, 0F);
			colormod = 0.15F;
		}
		GL11.glColor3f(ColorUtils.getR(tile.click_color)+colormod, ColorUtils.getG(tile.click_color)+colormod, ColorUtils.getB(tile.click_color)+colormod);
		IModelCustom click_model = ModelLibrary.getModel("click", tile.click);
		if(click_model != null)
			click_model.renderAll();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		

		FontRenderer fontrenderer = this.func_147498_b();
		GL11.glScalef(0.01F, -0.01F, 0.01F);
		GL11.glTranslatef(48.5F, -30.01F, 52.5F); //TODO: is it good?
		GL11.glRotatef(90F, -1F, 0F, 0F);
		GL11.glRotatef(180F, 0F, 1F, 0F);
		GL11.glDepthMask(false);
		fontrenderer.drawString(tile.text, -fontrenderer.getStringWidth(tile.text) / 2, 0, 0);
		GL11.glDepthMask(true);
	}
	
	// "Stolen" from https://github.com/pahimar/Equivalent-Exchange-3/blob/4230e778060bbe451ec2e4e7cf9de50253bd495b/src/main/java/com/pahimar/ee3/client/renderer/tileentity/TileEntityRendererAludel.java#L89-L115 ;)
	private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation)
	{
		if (orientation == ForgeDirection.NORTH)
		{
			GL11.glTranslated(x + 1, y, z);
			GL11.glRotatef(180F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		}
		else if (orientation == ForgeDirection.EAST)
		{
			GL11.glTranslated(x + 1, y, z + 1);
			GL11.glRotatef(90F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		}
		else if (orientation == ForgeDirection.SOUTH)
		{
			GL11.glTranslated(x, y, z + 1);
			GL11.glRotatef(0F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		}
		else if (orientation == ForgeDirection.WEST)
		{
			GL11.glTranslated(x, y, z);
			GL11.glRotatef(-90F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		}
	}
	
	
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
					ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
		GL11.glPushMatrix();
		switch(type) {
			case INVENTORY:
				GL11.glScalef(20F, 20F, 20F);
				GL11.glRotatef(90F, 1F, 0F, 0F);
				GL11.glTranslatef(-0.15F, 0F, -0.55F);
				GL11.glRotatef(-45F, -1F, -1F, 1F);
				break;
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
				GL11.glTranslatef(0.1F, 0F, -0.4F);
				break;
			default:
				break;
		}
		
		TileEntityButton tile = new TileEntityButton();
		if(itemStack.stackTagCompound != null) {
			tile.base = itemStack.stackTagCompound.getString("base");
			tile.click = itemStack.stackTagCompound.getString("click");
			tile.base_color = itemStack.stackTagCompound.getInteger("base_color");
			tile.click_color = itemStack.stackTagCompound.getInteger("click_color");
		}
		renderButton(tile);
		
		GL11.glPopMatrix();
	}
}
