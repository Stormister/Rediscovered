
package com.stormister.rediscovered;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import java.io.IOException;
import java.io.InputStream;

public class RenderTileEntityRedEgg extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/DragonEggRed.png");
	private ModelDragonEggRed model;
	
	public RenderTileEntityRedEgg()
	{
		this.model = new ModelDragonEggRed();
	}
	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) 
	{
		GL11.glPushMatrix();
			GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
			GL11.glRotatef(180, 0.0F, 0.0F, 1F);
			this.bindTexture(field_110871_a);
			GL11.glPushMatrix();
				this.model.renderAll();
			GL11.glPopMatrix();
		GL11.glPopMatrix(); //end
	}
}