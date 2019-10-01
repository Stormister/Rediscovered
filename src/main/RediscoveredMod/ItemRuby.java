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

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRuby extends Item
{
	String texture;
	private Minecraft mc;
	
	public ItemRuby(int par1, String texture)
    {
        super(par1);
        this.maxStackSize = 64;
        this.texture = texture;
    }
	
//	@Override
//	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,EntityPlayer par3EntityPlayer) {
//		DecorateBiomeEvent.Post e = new DecorateBiomeEvent.Post(par2World, par2World.rand, par3EntityPlayer.chunkCoordX, par3EntityPlayer.chunkCoordZ);
//		if((e.world.getBiomeGenForCoords(e.chunkX, e.chunkZ).biomeID == BiomeGenBase.ocean.biomeID))
//			Minecraft.getMinecraft().thePlayer.addChatMessage("You got something important!");
//		return par1ItemStack;
//	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
    	this.itemIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
    }
}
