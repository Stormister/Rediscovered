package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGreenVillager extends RenderLiving
{
	private static final ResourceLocation field_110871_a = new ResourceLocation("textures/entity/villager/villager.png");
	
    /** Model of the villager. */
    protected ModelGreenVillager villagerModel;

    public RenderGreenVillager()
    {
        super(new ModelGreenVillager(0.0F), 0.5F);
        this.villagerModel = (ModelGreenVillager)this.mainModel;
    }

    /**
     * Determines wether Villager Render pass or not.
     */
    protected int shouldVillagerRenderPass(EntityGreenVillager par1EntityGreenVillager, int par2, float par3)
    {
        return -1;
    }

    public void renderVillager(EntityGreenVillager par1EntityGreenVillager, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityGreenVillager, par2, par4, par6, par8, par9);
    }

    /**
     * Passes the Villager special render.
     */
    protected void passVillagerSpecialRender(EntityGreenVillager par1EntityGreenVillager, double par2, double par4, double par6) {}

    protected void renderVillagerEquipedItems(EntityGreenVillager par1EntityGreenVillager, float par2)
    {
        super.renderEquippedItems(par1EntityGreenVillager, par2);
    }

    protected void preRenderVillager(EntityGreenVillager par1EntityGreenVillager, float par2)
    {
        float var3 = 0.9375F;

        if (par1EntityGreenVillager.getGrowingAge() < 0)
        {
            var3 = (float)((double)var3 * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GL11.glScalef(var3, var3, var3);
    }

    /**
     * Passes the specialRender and renders it
     */
    protected void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.passVillagerSpecialRender((EntityGreenVillager)par1EntityLiving, par2, par4, par6);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.preRenderVillager((EntityGreenVillager)par1EntityLiving, par2);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldVillagerRenderPass((EntityGreenVillager)par1EntityLiving, par2, par3);
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderVillagerEquipedItems((EntityGreenVillager)par1EntityLiving, par2);
    }

    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderVillager((EntityGreenVillager)par1EntityLiving, par2, par4, par6, par8, par9);
    }
    
    protected ResourceLocation getSquidTextures(EntityGreenVillager par1EntitySquid)
    {
        return field_110871_a;
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getSquidTextures((EntityGreenVillager)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderVillager((EntityGreenVillager)par1Entity, par2, par4, par6, par8, par9);
    }
}
