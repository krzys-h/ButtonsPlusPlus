package pl.krzysh.minecraft.mod.buttonsplusplus.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ButtonLabelRenderer implements IItemRenderer {
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		boolean textRotate = false;
		switch(type) {
			case INVENTORY:
				GL11.glScalef(16F, 16F, 16F);
				GL11.glRotatef(45F, 1F, 1F, 0F);
				textRotate = true;
				break;
			case EQUIPPED:
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				break;
			case EQUIPPED_FIRST_PERSON:
				GL11.glScalef(1.25F, 1.25F, 1.25F);
				break;
			default:
				break;
		}

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(0.75F, 0.75F, 0.75F);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(0.05F, 0.2F, 0F);
		GL11.glVertex3f(0.05F, 0.8F, 0F);
		GL11.glVertex3f(0.95F, 0.8F, 0F);
		GL11.glVertex3f(0.95F, 0.2F, 0F);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		if(item.hasDisplayName()) {
			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
			GL11.glScalef(0.03F, -0.03F, 0.03F); //TODO: scale to fit
			if(textRotate)
				GL11.glRotatef(180F, 1F, 0F, 0F);
			else
				GL11.glRotatef(180F, 0F, 1F, 0F);
			float x = textRotate ? 1F : -1F;
			float x2 = textRotate ? 0 : 5F;
			GL11.glTranslatef(17.5F * x, 14F * x - x2, -0.1F * x);
			GL11.glDepthMask(false);
			fontrenderer.drawString(item.getDisplayName(), -fontrenderer.getStringWidth(item.getDisplayName()) / 2, 0, 0);
			GL11.glDepthMask(true);
		}

		GL11.glPopMatrix();
	}
}
