package com.stormister.rediscovered;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTable extends BlockContainer
{
    private final Random random = new Random();
    public final int field_94443_a;
    public boolean filled = false;

    protected BlockTable(int par2)
    {
        super(Material.wood);
        this.field_94443_a = par2;
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.6875F, 0.875F);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
    	return mod_Rediscovered.proxy.getBlockRender(this);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.6875F, 0.875F);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityTable)par1World.getTileEntity(par2, par3, par4)).func_94043_a(par6ItemStack.getDisplayName());
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return true;
    }

    /**
     * Checks the neighbor blocks to see if there is a chest there. Args: world, x, y, z
     */
    private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlock(par2, par3, par4) != this ? false : (par1World.getBlock(par2 - 1, par3, par4) == this ? true : (par1World.getBlock(par2 + 1, par3, par4) == this ? true : (par1World.getBlock(par2, par3, par4 - 1) == this ? true : par1World.getBlock(par2, par3, par4 + 1) == this)));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        TileEntityTable tileentitychest = (TileEntityTable)par1World.getTileEntity(par2, par3, par4);

        if (tileentitychest != null)
        {
            tileentitychest.updateContainingBlockInfo();
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
//    	TileEntityTable tileentitychest = (TileEntityTable)par1World.getTileEntity(par2, par3, par4);
//
//        if (tileentitychest != null)
//        {
//            for (int j1 = 0; j1 < 9; ++j1)
//            {
//                ItemStack itemstack = tileentitychest.getStackInSlot(j1);
//
//                if (itemstack != null)
//                {
//                    float f = this.random.nextFloat() * 0.8F + 0.1F;
//                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
//                    EntityItem entityitem;
//
//                    for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem))
//                    {
//                        int k1 = this.random.nextInt(21) + 10;
//
//                        if (k1 > itemstack.stackSize)
//                        {
//                            k1 = itemstack.stackSize;
//                        }
//
//                        itemstack.stackSize -= k1;
//                        entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
//                        float f3 = 0.05F;
//                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
//                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
//                        entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
//
//                        if (itemstack.hasTagCompound())
//                        {
//                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
//                        }
//                    }
//                }
//            }
//
//            par1World.func_96440_m(par2, par3, par4, par5);
//        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

//    /**
//     * Called upon block activation (right click on the block.)
//     */
//    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
//    {
//        if (par1World.isRemote)
//        {
//            return true;
//        }
//        ItemStack itemstack = par5EntityPlayer.getHeldItem();
//        
//        TileEntityTable tileentitychest = new TileEntityTable();
//
//        if (itemstack != null && !filled)
//        {
//        	tileentitychest.setTableContents(itemstack);
//        	
//        	filled = true;
//
//            if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
//            {
//            	par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
//            }
//            
//            return true;
//        }
//        else
//        {
//        	par5EntityPlayer.inventory.addItemStackToInventory(itemstack);
//        	filled = false;
//        	return true;
//        }
//    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World, int par1)
    {
    	TileEntityTable tileentitychest = new TileEntityTable();
        return tileentitychest;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Block idDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.Table;
    }
    
//    @Override
//	public void registerBlockIcons(IIconRegister reg)
//    {
//        this.blockIcon = reg.registerIcon(mod_Rediscovered.modid + ":" + "cryingobsidian");
//    }
}
