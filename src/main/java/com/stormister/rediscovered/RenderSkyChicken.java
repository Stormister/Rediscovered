package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSkyChicken extends RenderLiving
{
    private static final ResourceLocation field_110920_a = new ResourceLocation("textures/entity/chicken.png");

    public RenderSkyChicken(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderChicken(EntitySkyChicken par1EntitySkyChicken, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntitySkyChicken, par2, par4, par6, par8, par9);
    }

    protected float getWingRotation(EntitySkyChicken par1EntitySkyChicken, float par2)
    {
        float f1 = par1EntitySkyChicken.field_70888_h + (par1EntitySkyChicken.field_70886_e - par1EntitySkyChicken.field_70888_h) * par2;
        float f2 = par1EntitySkyChicken.field_70884_g + (par1EntitySkyChicken.destPos - par1EntitySkyChicken.field_70884_g) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntitySkyChicken)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.getWingRotation((EntitySkyChicken)par1EntityLivingBase, par2);
    }

    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntitySkyChicken)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getSquidTextures(EntitySkyChicken par1EntitySquid)
    {
        return field_110920_a;
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getSquidTextures((EntitySkyChicken)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderChicken((EntitySkyChicken)par1Entity, par2, par4, par6, par8, par9);
    }
}
