package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLantern extends Block
{
    public BlockLantern()
    {
        super(Material.air);
        this.setLightLevel(1.0f);
        this.setHardness(0.1f);
        setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setTickRandomly(true);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void updateTick(World w, int x, int y, int z, Random r)
    {
        if (w.getBlock(x, y, z) == mod_Rediscovered.Lantern)
        {
            w.setBlock(x, y, z, Blocks.air);
        }
    }

    @Override
    public boolean isAir(net.minecraft.world.IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).equals(this))
        {
            return true;
        }

        return false;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idPicked(int i, Random random, int j)
    {
        return 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int i, Random random, int j)
    {
        return 0;
    }
    
}
