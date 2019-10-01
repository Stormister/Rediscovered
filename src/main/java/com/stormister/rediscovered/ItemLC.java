package com.stormister.rediscovered;
 
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
 
public class ItemLC extends ItemArmor
{
        public ItemLC(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
        {
                super(par2EnumArmorMaterial, par3, par4);
        }
 
        @Override
    	public void registerIcons(IIconRegister reg){
    		if(this == mod_Rediscovered.LeatherChainHelmet)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCHelmet");
    		if(this == mod_Rediscovered.LeatherChainChest)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCChest");
    		if(this == mod_Rediscovered.LeatherChainLegs)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCLegs");
    		if(this == mod_Rediscovered.LeatherChainBoots)
    			this.itemIcon = reg.registerIcon(mod_Rediscovered.modid + ":LCBoots");
    	}
        
        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
        {
        	if(stack.getItem().equals(mod_Rediscovered.LeatherChainHelmet)|| stack.getItem().equals(mod_Rediscovered.LeatherChainChest)|| stack.getItem().equals(mod_Rediscovered.LeatherChainBoots)){
    			return mod_Rediscovered.modid + ":textures/models/leatherchain_1.png";
            }
                
        	if(stack.getItem().equals(mod_Rediscovered.LeatherChainLegs)){
    			return mod_Rediscovered.modid + ":textures/models/leatherchain_2.png";
            }
                
            else return null;
        }      
}