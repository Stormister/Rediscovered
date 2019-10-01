package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockRuby extends Block
{
	String texture;
	
    public BlockRuby(String texture)
    {
        super(Material.rock);
        this.texture = texture;
        this.setHarvestLevel("pickaxe", 1);
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
        return mod_Rediscovered.RubyBlock;
    }
    
//    public void registerBlockIcons(IIconRegister iconRegister) 
//    {
//    	this.blockIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
//    }
}
