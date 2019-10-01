package com.stormister.rediscovered;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.ForgeHooksClient;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLockedChest extends ModelBase
{
    /** The chest lid in the chest's model. */
    public ModelRenderer chestLid = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);

    /** The model of the bottom of the chest. */
    public ModelRenderer chestBelow;

    /** The chest's knob in the chest model. */
    public ModelRenderer chestKnob;

    public ModelLockedChest()
    {
        this.chestLid.addBox(-7.0F, 10F, -7.0F, 14, 5, 14, 0.0F);
        this.chestLid.rotationPointX = 0.0F;
        this.chestLid.rotationPointY = 0.0F;
        this.chestLid.rotationPointZ = 0.0F;
        this.chestKnob = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
        this.chestKnob.addBox(-1.0F, 0F, -15.0F, 2, 4, 1, 0.0F);
        this.chestKnob.rotationPointX = 8.0F;
        this.chestKnob.rotationPointY = 7.0F;
        this.chestKnob.rotationPointZ = 15.0F;
        this.chestBelow = (new ModelRenderer(this, 0, 19)).setTextureSize(64, 64);
        this.chestBelow.addBox(-7.0F, 14F, -7.0F, 14, 10, 14, 0.0F);
        this.chestBelow.rotationPointX = 0.0F;
        this.chestBelow.rotationPointY = 0.0F;
        this.chestBelow.rotationPointZ = 0.0F;
    }

    public void render(float scale, double x, double y, double z, float ang, float angY, boolean renderLantern, boolean lanternOn, boolean renderHeadTorch)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5 , z + 0.5);
        GL11.glPushMatrix();
        GL11.glRotatef(ang, 1f, 0f, 0f);
        GL11.glRotatef(angY, 0f, 1f, 0f);
        chestBelow.render(scale);
        chestLid.render(scale);
        chestKnob.render(scale);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    
    public void renderAll() 
    {
    	chestBelow.render(0.0625F);
    	chestLid.render(0.0625F);
    	chestKnob.render(0.0625F);
    }
}
