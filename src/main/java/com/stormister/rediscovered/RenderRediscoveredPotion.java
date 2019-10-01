package com.stormister.rediscovered;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRediscoveredPotion extends RenderSnowball
{
	public RenderRediscoveredPotion(RenderManager p_i46136_1_, RenderItem p_i46136_2_)
    {
        super(p_i46136_1_, mod_Rediscovered.RediscoveredPotion, p_i46136_2_);
    }

    public ItemStack func_177085_a(EntityRediscoveredPotion p_177085_1_)
    {
        return new ItemStack(this.field_177084_a, 1, p_177085_1_.metadata);
    }

    public ItemStack func_177082_d(Entity p_177082_1_)
    {
        return this.func_177085_a((EntityRediscoveredPotion)p_177082_1_);
    }
}