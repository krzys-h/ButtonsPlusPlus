package pl.krzysh.minecraft.mod.buttonsplusplus.client.render;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import pl.krzysh.minecraft.mod.buttonsplusplus.util.Color;

public class ButtonPartRenderer implements IItemRenderer {
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType rendertype, ItemStack itemstack, Object... data) {
		if(itemstack.stackTagCompound == null)
			return;

		GL11.glPushMatrix();
		switch(rendertype) {
			case INVENTORY:
				GL11.glTranslatef(2F, 2F, 0F);
				GL11.glScalef(12F, 12F, 12F);
				GL11.glRotatef(90F, 1F, 0F, 0F);
				GL11.glTranslatef(0F, 0F, -1F);
				break;
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
				GL11.glRotatef(-90F, 1F, 0F, 0F);
				break;
			default:
				break;
		}

		String type = itemstack.stackTagCompound.getString("type");
		String part = itemstack.stackTagCompound.getString("part");
		IModelCustom model = ModelLibrary.getModel(type, part);

		if(model != null) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			if(itemstack.stackTagCompound != null) {
				Color c = new Color(itemstack.stackTagCompound.getInteger("color"));
				GL11.glColor3f(c.getRf(), c.getGf(), c.getBf());
			}
			model.renderAll();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
	}
}
