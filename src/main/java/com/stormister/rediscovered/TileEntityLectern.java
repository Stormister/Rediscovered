package com.stormister.rediscovered;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;


public class TileEntityLectern extends TileEntity
{
	public boolean open;
	
	public void Open(EntityPlayer par5EntityPlayer)
	{
		open = true;
		par5EntityPlayer.addChatMessage(new ChatComponentText("Linked to TileEntityLectern(Now " + open + ")"));
	}
	
	public void notOpen(EntityPlayer par5EntityPlayer)
	{
		open = false;
		par5EntityPlayer.addChatMessage(new ChatComponentText("Linked to TileEntityLectern(Now " + open + ")"));
	}
}

