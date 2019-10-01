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

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukeBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemCalmRecord extends ItemRecord
{
	public final String recordName;
	String texture;
	private Minecraft mc;
	public ItemCalmRecord(int par1, String par2Str, String texture)
	{
		super(par1, par2Str);
		this.recordName = par2Str;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.texture = texture;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
    	this.itemIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
    }
		
		public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
		{
			if (par3World.getBlockId(par4, par5, par6) == Block.jukebox.blockID && par3World.getBlockMetadata(par4, par5, par6) == 0 && !mod_Rediscovered.Calm4MusicDisk)
			{
				if (par3World.isRemote)
				{
					return true;
				}
				else
				{
					((BlockJukeBox)Block.jukebox).insertRecord(par3World, par4, par5, par6, par1ItemStack);
//					par3World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par4, par5, par6, this.itemID);
//					par3World.playSoundAtEntity(par2EntityPlayer, mod_Rediscovered.modid + ":calm", 2.0F, 1.0F);
					--par1ItemStack.stackSize;
					return true;
				}
			}
			else
			{
				return false;
			}
		}
}