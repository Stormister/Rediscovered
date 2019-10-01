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
import net.minecraft.entity.EntityLiving;
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

public class BlockGearFloor extends BlockGearBase
{    
    protected BlockGearFloor()
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
        return canPlaceGearOn(par1World, par2, par3 - 1, par4);
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
   * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
   * their own) Args: x, y, z, neighbor blockID
   */
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
  {
      boolean flag = false;

      if (!par1World.getBlock(par2, par3 - 1, par4).getMaterial().isSolid())
      {
          flag = true;
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
