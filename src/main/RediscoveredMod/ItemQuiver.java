//*******************************************
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












//ItemTutorialPlate
//*******************************************
 
package RediscoveredMod;
 
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
 
public class ItemQuiver extends ItemArmor
{
	String texture;
 
        public ItemQuiver(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, String texture) 
        {
                super(par1, par2EnumArmorMaterial, par3, par4);
                this.texture = texture;
        }
 
        @SideOnly(Side.CLIENT)
        public void registerIcons(IconRegister iconRegister) 
        {
        	this.itemIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
        }
 
        public String getArmorTexture(ItemStack par1, Entity entity, int slot, int layer)
        {
            if ( par1.itemID==mod_Rediscovered.Quiver.itemID)
            {
                    return mod_Rediscovered.modid + ":textures/models/Quiver_1.png";
            }
            
            if ( par1.itemID==mod_Rediscovered.LeatherQuiver.itemID)
            {
                    return mod_Rediscovered.modid + ":textures/models/LeatherQuiver_1.png";
            }
            
            if ( par1.itemID==mod_Rediscovered.ChainQuiver.itemID)
            {
                    return mod_Rediscovered.modid + ":textures/models/ChainQuiver_1.png";
            }
            
            if ( par1.itemID==mod_Rediscovered.IronQuiver.itemID)
            {
                    return mod_Rediscovered.modid + ":textures/models/IronQuiver_1.png";
            }
            
            if ( par1.itemID==mod_Rediscovered.GoldQuiver.itemID)
            {
                    return mod_Rediscovered.modid + ":textures/models/GoldQuiver_1.png";
            }
            
            if ( par1.itemID==mod_Rediscovered.DiamondQuiver.itemID)
            {
                    return mod_Rediscovered.modid + ":textures/models/DiamondQuiver_1.png";
            }
            
            else return null;
            
        }      
       
       
       
}