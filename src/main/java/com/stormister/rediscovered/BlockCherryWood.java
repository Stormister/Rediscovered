package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockCherryWood extends Block
{
    /** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"cherry"};
    @SideOnly(Side.CLIENT)
    private IIcon iconArray;
    String texture;

    public BlockCherryWood(String texture)
    {
        super(Material.wood);
        this.texture = texture;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

//    @SideOnly(Side.CLIENT)
//
//    /**
//     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
//     */
//    public IIcon getIcon(int par1, int par2)
//    {
//        return this.iconArray;
//    }

//    @SideOnly(Side.CLIENT)
//
//    @Override
//	public void registerBlockIcons(IIconRegister reg)
//    {
//        this.blockIcon = reg.registerIcon(mod_Rediscovered.modid + ":" + "cryingobsidian");
//    }
}
