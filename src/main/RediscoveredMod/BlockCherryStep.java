//	  Copyright 2012-2014 Matthew Karcz
//
//	  This file is part of The Rediscovered Mod.
//
//    The Rediscovered Mod is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    The Rediscovered Mod is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with The Rediscovered Mod.  If not, see <http://www.gnu.org/licenses/>.












package RediscoveredMod;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCherryStep extends BlockHalfSlab
{
    /** The list of the types of step blocks. */
    public static final String[] blockStepTypes = new String[] {"wood"};

    public BlockCherryStep(int par1, boolean par2)
    {
    	super(par1, par2, Material.wood);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_Rediscovered.CherrySlab.blockID;
    }
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
	    if(par1World.getBlockId(par2, par3 - 1, par4) == mod_Rediscovered.CherrySlab.blockID)
	    {
		    par1World.setBlock(par2, par3, par4, 0);
		    par1World.setBlock(par2, par3 - 1, par4, mod_Rediscovered.CherryDoubleSlab.blockID);
	    }
	    
	    if(par1World.getBlockId(par2, par3 + 1, par4) == mod_Rediscovered.CherrySlab.blockID)
	    {
		    par1World.setBlock(par2, par3, par4, 0);
		    par1World.setBlock(par2, par3 + 1, par4, mod_Rediscovered.CherryDoubleSlab.blockID);
	    }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(mod_Rediscovered.CherrySlab.blockID, 2, par1 & 7);
    }
    
    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= blockStepTypes.length)
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + blockStepTypes[par1];
    }
    
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
             if (par1 != mod_Rediscovered.CherryDoubleSlab.blockID)
             {
             par3List.add(new ItemStack(par1, 1, 0));
             }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return mod_Rediscovered.CherryPlank.getBlockTextureFromSide(1);
    }

    
}
