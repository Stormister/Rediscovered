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

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderZombieHorse extends RenderLiving
{
	private static final ResourceLocation field_110871_a = new ResourceLocation("textures/entity/horse/horse_zombie.png");
		
        public RenderZombieHorse(ModelBase par1ModelBase, float par2)
        {
                super(par1ModelBase, par2);
        }

        public void renderMyExample(EntityZombieHorse par1EntityExampleH, double par2, double par4, double par6, float par8, float par9)
        {
                super.doRenderLiving(par1EntityExampleH, par2, par4, par6, par8, par9);
        }

        public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
        {
                renderMyExample((EntityZombieHorse)par1EntityLiving, par2, par4, par6, par8, par9);
        }

        public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
        {
                renderMyExample((EntityZombieHorse)par1Entity, par2, par4, par6, par8, par9);
        }
        
        // Added these two functions, to allow for scaling.
        protected void preRenderScale(EntityZombieHorse par1EntityExampleH, float par2)
        {
                // These values are x,y,z scale. Where 1.0F = 100%
                GL11.glScalef(1.0F, 1.0F, 1.0F);
        }
        
        protected ResourceLocation getSquidTextures(EntityZombieHorse par1EntitySquid)
        {
            return field_110871_a;
        }
        
        /**
         * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
         */
        protected ResourceLocation getEntityTexture(Entity par1Entity)
        {
            return this.getSquidTextures((EntityZombieHorse)par1Entity);
        }
        
        protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
        {
                preRenderScale((EntityZombieHorse)par1EntityLiving, par2);
        }
}