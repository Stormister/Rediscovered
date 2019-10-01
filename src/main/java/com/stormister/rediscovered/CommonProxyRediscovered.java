package com.stormister.rediscovered;

import java.util.HashMap;
import java.util.Map;

import com.stormister.rediscovered.TileEntityTable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxyRediscovered
{
		/** Used to store IExtendedEntityProperties data temporarily between player death and respawn */
		private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
		
		public void registerRenderThings(){};

		public void registerTileEntitySpecialRenderer(){
			
		}
		public int getBlockRender(Block blockID)
		{
			return -1;
		}
		
		/**
		* Adds an entity's custom data to the map for temporary storage
		* @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
		*/
		public static void storeEntityData(String name, NBTTagCompound compound)
		{
			extendedEntityData.put(name, compound);
		}

		/**
		* Removes the compound from the map and returns the NBT tag stored for name or null if none exists
		*/
		public static NBTTagCompound getEntityData(String name)
		{
			return extendedEntityData.remove(name);
		}
}
