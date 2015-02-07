package pl.krzysh.minecraft.mod.buttonsplusplus.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import pl.krzysh.minecraft.mod.buttonsplusplus.multipart.PartButton;
import pl.krzysh.minecraft.mod.buttonsplusplus.util.Color;
import codechicken.multipart.TMultiPart;

public class ButtonRenderer implements IItemRenderer, IPartRenderer {
	@Override
	public void renderPartAt(TMultiPart multiPart, double x, double y, double z) {
		PartButton part = (PartButton)multiPart;

		GL11.glPushMatrix();
		scaleTranslateRotate(x, y, z, part.orientation);
		renderButton(part);
		GL11.glPopMatrix();
	}

	private void renderButton(PartButton part) {
		GL11.glTranslatef((1 - part.size) / 2, 0F, (1 - part.size) / 2);
		GL11.glScalef(part.size, part.size, part.size);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		Color base_color = new Color(part.base_color);
		GL11.glColor3f(base_color.getRf(), base_color.getGf(), base_color.getBf());
		IModelCustom base_model = ModelLibrary.getModel("base", part.base);
		if(base_model != null)
			base_model.renderAll();

		Color click_color = new Color(part.click_color);
		if(part.active) {
			GL11.glTranslatef(0F, -0.125F, 0F);
			if(part.lamp) {
				//TODO: make this look more AWESOME :P
				Color lamp_color = new Color(part.lamp_color);
				click_color.setRf(0.5F * click_color.getRf() + 0.5F * lamp_color.getRf());
				click_color.setGf(0.5F * click_color.getGf() + 0.5F * lamp_color.getGf());
				click_color.setBf(0.5F * click_color.getBf() + 0.5F * lamp_color.getBf());
			}
		}
		GL11.glColor3f(click_color.getRf(), click_color.getGf(), click_color.getBf());
		IModelCustom click_model = ModelLibrary.getModel("click", part.click);
		if(click_model != null)
			click_model.renderAll();

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
		if(fontrenderer != null) {
			GL11.glScalef(0.01F, -0.01F, 0.01F);
			GL11.glTranslatef(48.5F, -30.01F, 52.5F); //TODO: is it good?
			GL11.glRotatef(90F, -1F, 0F, 0F);
			GL11.glRotatef(180F, 0F, 1F, 0F);
			GL11.glDepthMask(false);
			fontrenderer.drawString(part.text, -fontrenderer.getStringWidth(part.text) / 2, 0, 0);
			GL11.glDepthMask(true);
		}
	}

	// "Stolen" from https://github.com/pahimar/Equivalent-Exchange-3/blob/4230e778060bbe451ec2e4e7cf9de50253bd495b/src/main/java/com/pahimar/ee3/client/renderer/tileentity/TileEntityRendererAludel.java#L89-L115 ;)
	private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation) {
		if(orientation == ForgeDirection.NORTH) {
			GL11.glTranslated(x + 1, y, z);
			GL11.glRotatef(180F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		} else if(orientation == ForgeDirection.EAST) {
			GL11.glTranslated(x + 1, y, z + 1);
			GL11.glRotatef(90F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		} else if(orientation == ForgeDirection.SOUTH) {
			GL11.glTranslated(x, y, z + 1);
			GL11.glRotatef(0F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		} else if(orientation == ForgeDirection.WEST) {
			GL11.glTranslated(x, y, z);
			GL11.glRotatef(-90F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
		}
	}

	public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
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

		PartButton part = new PartButton();
		if(itemStack.stackTagCompound != null) {
			part.base = itemStack.stackTagCompound.getString("base");
			part.click = itemStack.stackTagCompound.getString("click");
			part.base_color = itemStack.stackTagCompound.getInteger("base_color");
			part.click_color = itemStack.stackTagCompound.getInteger("click_color");
		}
		renderButton(part);

		GL11.glPopMatrix();
	}
}
