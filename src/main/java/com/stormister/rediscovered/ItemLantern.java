package com.stormister.rediscovered;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLantern extends Item
{
	String texture;
	
    public ItemLantern(String texture)
    {
        super();
        this.setCreativeTab(CreativeTabs.tabTools);
        this.canRepair = false;
        this.setMaxStackSize(1);
        this.setMaxDamage(32);
        this.texture = texture;
    }

    @Override
    public void onUpdate(ItemStack i, World w, Entity e, int slot, boolean par5)
    {
        // Extinguish the lantern if it is not on the hotbar
        if (e instanceof EntityPlayer)
        {
            EntityPlayer p = (EntityPlayer) e;
            ItemStack blah = new ItemStack(mod_Rediscovered.ItemLantern, 1);
            if (slot >=0 && i.equals(blah))
            {
                p.inventory.mainInventory[slot] = new ItemStack(mod_Rediscovered.ItemLantern, 1);
            }
        }
    }
    
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par3World.getBlock(par4, par5, par6).equals(Blocks.snow))
        {
            if (par7 == 0)
            {
                --par5;
            }

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

            if (par3World.getBlock(par4, par5, par6) != Blocks.air)
            {
                return false;
            }
        }

        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
            if (mod_Rediscovered.LanternPhys.canPlaceBlockAt(par3World, par4, par5, par6))
            {
                --par1ItemStack.stackSize;
                par3World.setBlock(par4, par5, par6, mod_Rediscovered.LanternPhys);
            }

            return true;
        }
    }

}
