package com.stormister.rediscovered;

import static org.lwjgl.opengl.GL11.GL_COMPILE_AND_EXECUTE;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.primitives.SignedBytes;

@SideOnly(Side.CLIENT)
public class RenderTileEntityTable extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_110871_a = new ResourceLocation(mod_Rediscovered.modid + ":" + "textures/models/ChairTableWood.png");
	
    private Random random;

    private RenderBlocks renderBlocks;

    private RenderItem itemRenderer;
    
    ItemStack[] array = new ItemStack[1];
    
    ItemStack item;

    private static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F },
            { 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F }, };
    /** The normal small chest model. */
    private ModelTable chestModel = new ModelTable();

    public RenderTileEntityTable()
    {
        random = new Random();
        renderBlocks = new RenderBlocks();
        itemRenderer = new RenderItem() {
            @Override
            public byte getMiniBlockCount(ItemStack stack, byte original) {
                return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 15) + 1);
            }
            @Override
            public byte getMiniItemCount(ItemStack stack, byte original) {
                return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 7) + 1);
            }
            @Override
            public boolean shouldBob() {
                return false;
            }
            @Override
            public boolean shouldSpreadItems() {
                return false;
            }
        };
        itemRenderer.setRenderManager(RenderManager.instance);
    }

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void render(TileEntityTable par1TileEntityChest, double par2, double par4, double par6, float par8)
    {
        int i;
        this.bindTexture(field_110871_a);

        if (!par1TileEntityChest.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            Block block = par1TileEntityChest.getBlockType();
            i = par1TileEntityChest.getBlockMetadata();

            if (block instanceof BlockTable && i == 0)
            {
                i = par1TileEntityChest.getBlockMetadata();
            }
        }
            ModelTable modelchest;
            modelchest = this.chestModel;
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
            float f2;
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            
            
            //***********************************************
            //RENDERS ITEM ON TABLE
            //***********************************************
            
//            ItemStack itemstack = par1TileEntityChest.getTopItemStacks();
//
//            if (itemstack != null)
//            {
//                EntityItem entityitem = new EntityItem(par1TileEntityChest.worldObj, 0.0D, 0.0D, 0.0D, itemstack);
//                entityitem.getEntityItem().stackSize = 1;
//                entityitem.hoverStart = 0.0F;
//                GL11.glPushMatrix();
//                GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[par1TileEntityChest.hangingDirection], -0.18F, -0.453125F * (float)Direction.offsetZ[par1TileEntityChest.hangingDirection]);
//                GL11.glRotatef(180.0F + par1TileEntityChest.rotationYaw, 0.0F, 1.0F, 0.0F);
//                GL11.glRotatef((float)(-90 * par1TileEntityChest.getRotation()), 0.0F, 0.0F, 1.0F);
//                RenderItem.renderInFrame = true;
//                RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
//                RenderItem.renderInFrame = false;
//                GL11.glPopMatrix();
//            }
        }    

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.render((TileEntityTable)par1TileEntity, par2, par4, par6, par8);
    }
}
