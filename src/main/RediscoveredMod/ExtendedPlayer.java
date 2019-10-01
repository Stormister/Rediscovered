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

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "RediscoveredPlayerSkyRespawnCoords";

	private final EntityPlayer player;

	private int respawnX = 0, respawnY = 64, respawnZ = 0;

	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
	}
	
	/**
	* Used to register these extended properties for the player during EntityConstructing event
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}
	
	/**
	* Returns ExtendedPlayer properties for player
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
	
		properties.setInteger("RespawnX", this.respawnX);
		properties.setInteger("RespawnY", this.respawnY);
		properties.setInteger("RespawnZ", this.respawnZ);
		
		
		compound.setTag(EXT_PROP_NAME, properties);
	}
	
	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.respawnX = properties.getInteger("RespawnX");
		this.respawnY = properties.getInteger("RespawnY");
		this.respawnZ = properties.getInteger("RespawnZ");		
	}
	
	@Override
	public void init(Entity entity, World world)
	{
	}
	
	/**
	* Returns true if the amount of mana was consumed or false
	* if the player's current mana was insufficient
	*/
	public void setRespawn(int x, int y, int z)
	{
		respawnX = x;
		respawnY = y;
		respawnZ = z;
		System.out.println("[REDISCOVERED]Respawn coords from NBT: " + this.respawnX + "/" + this.respawnY + "/" + this.respawnZ);
	}
	
	public ChunkCoordinates getRespawn()
	{
		ChunkCoordinates coordinates = new ChunkCoordinates(respawnX, respawnY, respawnZ);
		return coordinates;
	}
}