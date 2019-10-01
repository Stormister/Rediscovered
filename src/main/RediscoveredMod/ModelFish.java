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
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFish extends ModelBase
{
  //fields
    ModelRenderer TopFin;
    ModelRenderer LeftFin;
    ModelRenderer RightFin;
    ModelRenderer head;
    ModelRenderer TailTop;
    ModelRenderer TailBottom;
    ModelRenderer Shape1;
  
  public ModelFish()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      TopFin = new ModelRenderer(this, 20, 0);
      TopFin.addBox(0F, 0F, 0F, 2, 0, 4);
      TopFin.setRotationPoint(-1F, 18F, -3F);
      TopFin.setTextureSize(64, 32);
      TopFin.mirror = true;
      setRotation(TopFin, 0.2731595F, 0F, 0F);
      LeftFin = new ModelRenderer(this, 40, 0);
      LeftFin.addBox(0F, 0F, 0F, 2, 2, 2);
      LeftFin.setRotationPoint(-0.75F, 19F, -1F);
      LeftFin.setTextureSize(64, 32);
      LeftFin.mirror = true;
      setRotation(LeftFin, 0F, 0.6494897F, 0F);
      RightFin = new ModelRenderer(this, 40, 0);
      RightFin.addBox(0F, 0F, 0F, 2, 2, 2);
      RightFin.setRotationPoint(-1F, 19F, -2F);
      RightFin.setTextureSize(64, 32);
      RightFin.mirror = true;
      setRotation(RightFin, 0F, -0.6498035F, 0F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 2, 4, 8);
      head.setRotationPoint(3F, 26F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      TailTop = new ModelRenderer(this, 32, 0);
      TailTop.addBox(-1F, 16.5F, -7F, 2, 2, 2);
      TailTop.setRotationPoint(0F, 0F, 3F);
      TailTop.setTextureSize(64, 32);
      TailTop.mirror = true;
      setRotation(TailTop, 0.4118977F, -0.7853982F, 0F);
      TailBottom = new ModelRenderer(this, 32, 0);
      TailBottom.addBox(-1F, 18F, 9F, 2, 2, 2);
      TailBottom.setRotationPoint(0F, 0F, 3F);
      TailBottom.setTextureSize(64, 32);
      TailBottom.mirror = true;
      setRotation(TailBottom, -0.4118977F, -0.7853982F, 0F);
      Shape1 = new ModelRenderer(this, 21, 4);
      Shape1.addBox(0F, 0F, 0F, 2, 2, 0);
      Shape1.setRotationPoint(-1F, 16.9F, 0.9F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    TopFin.render(f5);
    LeftFin.render(f5);
    RightFin.render(f5);
    head.render(f5);
    TailTop.render(f5);
    TailBottom.render(f5);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
	  this.TailTop.rotateAngleY = MathHelper.sin(f2 * 0.112F) * 0.5F;
	    this.TailBottom.rotateAngleY = MathHelper.sin(f2 * 0.112F) * 0.5F;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.TailTop.rotateAngleY = MathHelper.sin(f2 * 0.112F) * 0.5F;
    this.TailBottom.rotateAngleY = MathHelper.sin(f2 * 0.112F) * 0.5F;
  }

}
