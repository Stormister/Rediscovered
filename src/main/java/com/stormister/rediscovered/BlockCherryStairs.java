package com.stormister.rediscovered;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;


public class BlockCherryStairs extends BlockStairs
{
	String texture;
	
	public BlockCherryStairs(Block par2Block, int par3)
	{
		super(par2Block, par3);
		setLightOpacity(0);
	}
    
//    /**
//     * Called when the block is placed in the world.
//     */
//    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
//    {
//    	BlockEntity.createEntity(par1World, par2, par3, par4, 0.5F);
//    }

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
        return mod_Rediscovered.CherryStairs;
    }
    
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister iconRegister) 
//    {
//    	this.blockIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
//    }
}
