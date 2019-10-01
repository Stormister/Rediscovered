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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSpikes extends Item
{
	String texture;
	
    public ItemSpikes(int par1, String texture)
    {
        super(par1);
        this.texture = texture;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 == 0)
        {
            return false;
        }
        else if (!par3World.getBlockMaterial(par4, par5, par6).isSolid())
        {
            return false;
        }
        else
        {
            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }

            if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
            {
                return false;
            }
            else if (!mod_Rediscovered.Spikes.canPlaceBlockAt(par3World, par4, par5, par6))
            {
                return false;
            }
            else
            {
                if (par7 == 1)
                {
                    int i1 = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;

                    par3World.setBlock(par4, par5, par6, mod_Rediscovered.Spikes.blockID, i1, 2);
                }
                else
                {
                	  int l = par3World.getBlockId(par4, par5, par6 - 1);
	                  int i1 = par3World.getBlockId(par4, par5, par6 + 1);
	                  int j1 = par3World.getBlockId(par4 - 1, par5, par6);
	                  int k1 = par3World.getBlockId(par4 + 1, par5, par6);
	                  byte b0 = 0;
	                  int l1 = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	          
	                  if (l1 == 0)
	                  {
	                      b0 = 2;
	                  }
	          
	                  if (l1 == 1)
	                  {
	                      b0 = 3;
	                  }
	          
	                  if (l1 == 2)
	                  {
	                      b0 = 4;
	                  }
	          
	                  if (l1 == 3)
	                  {
	                      b0 = 5;
	                  }
                      par3World.setBlock(par4, par5, par6, mod_Rediscovered.SpikesSide.blockID, b0, 2);
                }

                --par1ItemStack.stackSize;
                

                return true;
            }
        }
    }
    
    
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
    	this.itemIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
    }
}