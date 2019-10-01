package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemScarecrow extends Item
{
	String texture;
	
    public ItemScarecrow(String texture)
    {
        super();
        this.texture = texture;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	if(!world.isRemote){
		    EntityScarecrow sheep = new EntityScarecrow(world);
		    sheep.setLocationAndAngles(par4 + 0.5, par5+1, par6 + 0.5, player.rotationYaw, player.rotationPitch);
		    world.spawnEntityInWorld(sheep);
    		--itemStack.stackSize;
	    }
    	else
    	{
    		return false;
    	}
    	return true;
    }
}
