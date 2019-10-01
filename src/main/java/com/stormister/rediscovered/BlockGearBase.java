package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockGearBase extends Block
{
    @SideOnly(Side.CLIENT)
    protected IIcon[] theIcon;

    protected BlockGearBase(Material par2Material)
    {
        super(par2Material);
        this.setHarvestLevel("pickaxe", 0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.theIcon[1] : this.theIcon[0];
    }
    

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.theIcon = new IIcon[] {par1IconRegister.registerIcon(mod_Rediscovered.modid + ":gear_animated"), par1IconRegister.registerIcon(mod_Rediscovered.modid + ":gear_animated")};
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_94424_b(String par0Str)
    {
        return null;
    }
}
