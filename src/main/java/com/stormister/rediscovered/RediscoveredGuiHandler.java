package com.stormister.rediscovered;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class RediscoveredGuiHandler implements IGuiHandler {

	public RediscoveredGuiHandler(){
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
		
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
		TileEntity tileentity = world.getTileEntity(x, y, z);
		
		switch(ID){
			case mod_Rediscovered.guiIDLockedChest:
				if(tileentity instanceof TileEntityLockedChest){
					return new GuiLockedChest(player);
				}
		}
		return null;
	}

}
