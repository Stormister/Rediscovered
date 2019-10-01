package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemGear extends Item
{
	String texture;
	
    public ItemGear(String texture)
    {
        super();
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
        else if (par3World.getBlock(par4, par5, par6).isAir(par3World, par7, par7, par7))
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
            else if ((!mod_Rediscovered.GearFloor.canPlaceBlockAt(par3World, par4, par5, par6) && !mod_Rediscovered.GearWall.canPlaceBlockAt(par3World, par4, par5, par6)) || par3World.getBlock(par4, par5, par6) != Blocks.air)
            {
                return false;
            }
            else
            {
                if (par7 == 1 && mod_Rediscovered.GearFloor.canPlaceBlockAt(par3World, par4, par5, par6) && mod_Rediscovered.GearWall.canPlaceBlockAt(par3World, par4, par5, par6))
                {
                    int i1 = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                    par3World.setBlock(par4, par5, par6, mod_Rediscovered.GearFloor);
                }
                else if(!mod_Rediscovered.GearWall.canPlaceBlockAt(par3World, par4, par5, par6) && mod_Rediscovered.GearFloor.canPlaceBlockAt(par3World, par4, par5, par6))
                {
                	int i1 = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                    par3World.setBlock(par4, par5, par6, mod_Rediscovered.GearFloor);

                }
                else if(par7 == 1 && mod_Rediscovered.GearWall.canPlaceBlockAt(par3World, par4, par5, par6) && !mod_Rediscovered.GearFloor.canPlaceBlockAt(par3World, par4, par5, par6))
                {
                    return false;
                }
                else
                {
                	par3World.setBlock(par4, par5, par6, mod_Rediscovered.GearWall, par7, 2);
                    return true;
                }

                --par1ItemStack.stackSize;
                

                return true;
            }
        }
    }
}