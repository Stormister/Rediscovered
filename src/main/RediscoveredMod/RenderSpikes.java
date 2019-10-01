//	  Copyright 2012-2014 Matthew Karcz
//
//	  This file is part of The Rediscovered Mod.
//
//    The Rediscovered Mod is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    The Rediscovered Mod is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with The Rediscovered Mod.  If not, see <http://www.gnu.org/licenses/>.













package RediscoveredMod;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import java.io.IOException;
import java.io.InputStream;

public class RenderSpikes extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/Spikes.png");
	
	public RenderSpikes()
	{
		model = new ModelSpikes();
	}
	
	public void renderAModelAt(TileEntitySpikes tile, double d, double d1, double d2, float f) 
	{
	
		int rotation = 0;
		if(tile.worldObj != null)
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
	
	private ModelSpikes model;
	
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderAModelAt((TileEntitySpikes)par1TileEntity, par2, par4, par6, par8);
	}
}