
package com.stormister.rediscovered;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.io.InputStream;

public class RenderTileEntityChair extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/ChairTableWood.png");
	
	public RenderTileEntityChair()
	{
		model = new ModelChair();
	}
	
	public void renderAModelAt(TileEntityChair tile, double d, double d1, double d2, float f) 
	{
	
		int rotation = 0;
		if(tile != null && tile.hasWorldObj())
		{
		rotation = tile.getBlockMetadata();
		}
		this.bindTexture(field_110871_a);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotation*90, 0.0F, 1.0F, 0.0F);
		model.renderAll();
		GL11.glPopMatrix(); 
		//end
	}
	
	/**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getArrowTextures((EntityArrow)par1Entity);
    }
    
    protected ResourceLocation getArrowTextures(EntityArrow par1EntityArrow)
    {
        return field_110871_a;
    }
	
	private ModelChair model;
	
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderAModelAt((TileEntityChair)par1TileEntity, par2, par4, par6, par8);
	}
}