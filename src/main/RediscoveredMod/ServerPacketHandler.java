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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        EntityPlayer sender = (EntityPlayer) player;

        if (packet.channel.equals(Resources.PACKET_CHANNEL))
        {
            DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
            int id = -1;

            try
            {
                id = data.readInt();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void toggleHotbarLantern(EntityPlayer p, int slot)
    {
        if (slot < 0 || slot > 8)
        {
            return;
        }

        ItemStack item = p.inventory.mainInventory[slot];

        if (item != null && (item.itemID == mod_Rediscovered.ItemLantern.itemID))
        {
            if (item.itemID == mod_Rediscovered.ItemLantern.itemID)
            {
                p.inventory.mainInventory[slot] = new ItemStack(mod_Rediscovered.ItemLantern, 1);
            }
            else
            {
                
            }
        }
    }

    
}
