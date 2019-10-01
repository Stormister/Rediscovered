package com.stormister.rediscovered;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTable extends ModelBase
{
  //fields
    ModelRenderer leg1;
    ModelRenderer leg3;
    ModelRenderer leg2;
    ModelRenderer leg4;
    ModelRenderer Top;
  
  public ModelTable()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      leg1 = new ModelRenderer(this, 0, 0);
      leg1.addBox(8F, -8F, 8F, 2, 9, 2);
      leg1.setRotationPoint(-6F, 15F, -6F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 0, 0);
      leg3.addBox(8F, -8F, 8F, 2, 9, 2);
      leg3.setRotationPoint(4F, 15F, 4F);
      leg3.setTextureSize(64, 32);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 0, 0);
      leg2.addBox(8F, -8F, 8F, 2, 9, 2);
      leg2.setRotationPoint(4F, 15F, -6F);
      leg2.setTextureSize(64, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 0, 0);
      leg4.addBox(8F, -8F, 8F, 2, 9, 2);
      leg4.setRotationPoint(-6F, 15F, 4F);
      leg4.setTextureSize(64, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 14);
      Top.addBox(8F, -8F, 8F, 12, 2, 12);
      Top.setRotationPoint(-6F, 13F, -6F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    leg1.render(f5);
    leg3.render(f5);
    leg2.render(f5);
    leg4.render(f5);
    Top.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void renderAll() 
  {
	  leg1.render(0.0625F);
	  leg2.render(0.0625F);
	  leg3.render(0.0625F);
	  leg4.render(0.0625F);
	  Top.render(0.0625F);
  }

}
