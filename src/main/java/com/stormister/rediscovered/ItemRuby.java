package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
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
	
	public ItemRuby(String texture)
    {
        super();
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
	
//	@SideOnly(Side.CLIENT)
//    public void registerIcons(IIconRegister iconRegister) 
//    {
//    	this.itemIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
//    }
}
