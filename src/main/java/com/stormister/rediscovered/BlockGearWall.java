package com.stormister.rediscovered;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGearWall extends BlockGearBase
{
    protected BlockGearWall()
    {
        super(Material.iron);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.setHarvestLevel("pickaxe", 0);
    }
    
    @Override
    public int tickRate(World p_149738_1_)
    {
    	return 2;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
    
    /**
     * Gets if we can place a torch on a block.
     */
    private static boolean canPlaceGearOn(World par1World, int par2, int par3, int par4)
    {
        if (par1World.doesBlockHaveSolidTopSurface(par1World, par2, par3, par4))
        {
            return true;
        }
        else
        {
            Block l = par1World.getBlock(par2, par3, par4);
            return (l != null && l.canPlaceTorchOnTop(par1World, par2, par3, par4));
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlock(par2 - 1, par3, par4).isSideSolid(par1World, par2 - 1, par3, par4, EAST) ||
               par1World.getBlock(par2 + 1, par3, par4).isSideSolid(par1World, par2 + 1, par3, par4, WEST) ||
               par1World.getBlock(par2, par3, par4 - 1).isSideSolid(par1World, par2, par3, par4 - 1, SOUTH) ||
               par1World.getBlock(par2, par3, par4 + 1).isSideSolid(par1World, par2, par3, par4 + 1, NORTH) || 
               canPlaceGearOn(par1World, par2, par3 - 1, par4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
            int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);

            if (l == 2)
            {
            	this.setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 1.0F, 1.0F);
            }

            if (l == 3)
            {
            	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
            }

            if (l == 4)
            {
            	this.setBlockBounds(0.9375F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if (l == 5)
            {
            	this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
            }
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
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
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);

        if (!p_149726_1_.isRemote)
        {
        	p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_, p_149726_4_, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_ + 1, p_149726_4_, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_ - 1, p_149726_4_, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_ - 1, p_149726_3_, p_149726_4_, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_ + 1, p_149726_3_, p_149726_4_, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_, p_149726_4_ - 1, this);
            p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_, p_149726_4_ + 1, this);
        }
    }
    
    /**
   * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
   * their own) Args: x, y, z, neighbor blockID
   */
  @Override
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
  {
      boolean flag = false;

          int i1 = par1World.getBlockMetadata(par2, par3, par4);
          flag = true;

          if (i1 == 2 && par1World.getBlock(par2, par3, par4 + 1).getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 3 && par1World.getBlock(par2, par3, par4 - 1).getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 4 && par1World.getBlock(par2 + 1, par3, par4).getMaterial().isSolid())
          {
              flag = false;
          }

          if (i1 == 5 && par1World.getBlock(par2 - 1, par3, par4).getMaterial().isSolid())
          {
              flag = false;
          }
      
      if (flag)
      {
          this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
          par1World.setBlockToAir(par2, par3, par4);
      }
      super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
  }
    
    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
    {
        return 15;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon func_94424_b(String par0Str)
    {
        return par0Str == mod_Rediscovered.modid + ":gear_animated" ? this.theIcon[0] : null;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.ItemGear;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return new ItemStack(mod_Rediscovered.ItemGear);
    }
}
