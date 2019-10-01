package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockRubyOre extends BlockOre
{
	String texture;
	
    public BlockRubyOre(String texture)
    {
        super();
        this.texture = texture;
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        
        int var8 = 0;

         if (this.equals(mod_Rediscovered.RubyOre))
         {
             var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 5);
         }

         this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return mod_Rediscovered.gemRuby;
    }
}
