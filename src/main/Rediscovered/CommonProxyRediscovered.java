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












package Rediscovered;

import java.util.HashMap;
import java.util.Map;

import RediscoveredMod.ContainerTable;
import RediscoveredMod.TileEntityTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxyRediscovered
{
	/** Used to store IExtendedEntityProperties data temporarily between player death and respawn */
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
	
		public void registerRenderThings(){};
		
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
