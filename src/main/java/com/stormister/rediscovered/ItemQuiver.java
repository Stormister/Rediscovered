package com.stormister.rediscovered;
 
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.IIcon;
 
public class ItemQuiver extends ItemArmor
{ 
	public ItemQuiver(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
    {
            super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
	public void registerIcons(IIconRegister reg){
		if(this == mod_Rediscovered.Quiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":Quiver");
		if(this == mod_Rediscovered.LeatherQuiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LQuiver");
		if(this == mod_Rediscovered.ChainQuiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":CQuiver");
		if(this == mod_Rediscovered.GoldQuiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":GQuiver");
		if(this == mod_Rediscovered.IronQuiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":IQuiver");
		if(this == mod_Rediscovered.DiamondQuiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":DQuiver");
		if(this == mod_Rediscovered.LeatherChainQuiver)
			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":SQuiver");
	}
    
    @Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
    	if(stack.getItem().equals(mod_Rediscovered.Quiver)){
			return mod_Rediscovered.modid + ":textures/models/Quiver_1.png";
        }
    	if(stack.getItem().equals(mod_Rediscovered.LeatherQuiver)){
			return mod_Rediscovered.modid + ":textures/models/LeatherQuiver_1.png";
        }            
    	if(stack.getItem().equals(mod_Rediscovered.ChainQuiver)){
			return mod_Rediscovered.modid + ":textures/models/ChainQuiver_1.png";
        }
    	if(stack.getItem().equals(mod_Rediscovered.GoldQuiver)){
			return mod_Rediscovered.modid + ":textures/models/GoldQuiver_1.png";
        }
    	if(stack.getItem().equals(mod_Rediscovered.IronQuiver)){
			return mod_Rediscovered.modid + ":textures/models/IronQuiver_1.png";
        }
    	if(stack.getItem().equals(mod_Rediscovered.DiamondQuiver)){
			return mod_Rediscovered.modid + ":textures/models/DiamondQuiver_1.png";
        }
    	if(stack.getItem().equals(mod_Rediscovered.LeatherChainQuiver)){
			return mod_Rediscovered.modid + ":textures/models/StuddedQuiver_1.png";
        }
            
        else return null;
	}
}