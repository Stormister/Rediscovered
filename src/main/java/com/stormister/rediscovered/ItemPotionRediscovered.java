package com.stormister.rediscovered;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemPotionRediscovered extends Item
{
	public IIcon[] icons = new IIcon[6];
    public ItemPotionRediscovered()
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabBrewing);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
    
    public static String getType(ItemStack itemStack){
    	if(itemStack.getItemDamage() == 0 || itemStack.getItemDamage() == 100)
    		return "Nausea";
    	else if(itemStack.getItemDamage() == 1 || itemStack.getItemDamage() == 101)
    		return "Blindness";
    	else if(itemStack.getItemDamage() == 2 || itemStack.getItemDamage() == 102)
    		return "Dullness";
    	else
    		return "";
    }

    public ItemStack onEaten(ItemStack itemStack, World p_77654_2_, EntityPlayer entityPlayer)
    {
    	if(getType(itemStack) == "Nausea")
    		entityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 30 * 20, 6));
    	else if(getType(itemStack) == "Blindness")
    		entityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 30 * 20, 6));
    	else if(getType(itemStack) == "Dullness")
    		entityPlayer.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 30 * 20, 6));
	    
	    if (!entityPlayer.capabilities.isCreativeMode)
        {
	    	itemStack.stackSize--;
            if (itemStack.stackSize <= 0)
            {
                return new ItemStack(Items.glass_bottle);
            }

            entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
	
	    return itemStack;
    }    
    
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (isSplash(itemStackIn.getItemDamage()))
        {
            if (!playerIn.capabilities.isCreativeMode)
            {
                --itemStackIn.stackSize;
            }

            worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote)
            {
                worldIn.spawnEntityInWorld(new EntityRediscoveredPotion(worldIn, playerIn, itemStackIn));
            }

            return itemStackIn;
        }
        else
        {
            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
            return itemStackIn;
        }
    }
    
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	if(getType(itemStack) == "Nausea")
            par3List.add("\u00a77" + "Dizziness (0:30)");  
    	else if(getType(itemStack) == "Blindness")
            par3List.add("\u00a77" + "Blindness (0:30)"); 
    	else if(getType(itemStack) == "Dullness")
            par3List.add("\u00a77" + "Slow Mining (0:30)"); 
        
    }
    
    public static boolean isSplash(int meta)
    {
        return (meta & 100) != 0;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + getType(stack) + (isSplash(stack.getItemDamage()) ? ".Splash" : "");
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
        
        subItems.add(new ItemStack(itemIn, 1, 100));
        subItems.add(new ItemStack(itemIn, 1, 101));
        subItems.add(new ItemStack(itemIn, 1, 102));
    }
    
    @Override
    public IIcon getIconFromDamage(int meta) {
        if ((meta>2 && meta<100) || meta>102)
            meta = 0;

        if(meta>=100)
        	return this.icons[meta-97];
        else
        	return this.icons[meta];
    }
    
    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < 3; i ++) {
            this.icons[i] = reg.registerIcon(mod_Rediscovered.modid + ":potion_" + i);
            this.icons[i+3] = reg.registerIcon(mod_Rediscovered.modid + ":potion_" + (i+100));
        }
    }
}
