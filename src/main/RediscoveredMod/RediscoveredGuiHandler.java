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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class RediscoveredGuiHandler implements IGuiHandler {

	public RediscoveredGuiHandler( ){
		NetworkRegistry.instance().registerGuiHandler(mod_Rediscovered.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
			case mod_Rediscovered.guiIDLockedChest:
				if(tileentity instanceof TileEntityLockedChest){
					return new ContainerLockedChest(player);
				}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		
		switch(ID){
			case mod_Rediscovered.guiIDLockedChest:
				if(tileentity instanceof TileEntityLockedChest){
					return new GuiLockedChest(player);
				}
		}
		return null;
	}

}
