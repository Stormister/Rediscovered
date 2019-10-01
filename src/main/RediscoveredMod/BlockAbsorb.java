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

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAbsorb extends Block
{
	String texture;
	
    public BlockAbsorb(int i, String texture)
    {
        super(i, Material.sponge);
        this.texture = texture;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate()
    {
        return 5;
    }

    public byte getType(World world, int i, int j, int k)
    {
        return (byte)world.getBlockMetadata(i, j, k);
    }

    public byte getRadius(World world, int i, int j, int k)
    {
        return (byte)(getType(world, i, j, k) == 0 || getType(world, i, j, k) == 1 ? 4 : 6);
    }

    public int makeStill(World world, int i, int j, int k, byte byte0)
    {
        Material material = world.getBlockMaterial(i, j, k);

        if (material == Material.water && (byte0 == 0 || byte0 == 2) && world.getBlockId(i, j, k) != Block.waterStill.blockID)
        {
            world.setBlock(i, j, k, Block.waterStill.blockID);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public int absorbBlock(World world, int i, int j, int k, byte byte0)
    {
        Material material = world.getBlockMaterial(i, j, k);

        if (material == Material.water && (byte0 == 0 || byte0 == 2))
        {
            world.setBlock(i, j, k, 0);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Called whenever the block is removed.
     */
    public void breakBlock(World world, int i, int j, int k, int l, int m)
    {
    	super.breakBlock(world, i, j, k, l, m);
        modifyWorld(world, i, j, k, false);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int i, int j, int k)
    {
        modifyWorld(world, i, j, k, true);
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        modifyWorld(world, i, j, k, true);
        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
    }

//    /**
//     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
//     * block.
//     */
//    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
//    {
//        world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
//        return true;
//    }

    public void modifyWorld(World world, int i, int j, int k, boolean flag)
    {
        byte byte0 = getRadius(world, i, j, k);
        byte byte1 = getType(world, i, j, k);
        int l = 0;

        for (int i1 = i - byte0; i1 <= i + byte0; i1++)
        {
            for (int j1 = j - byte0; j1 <= j + byte0; j1++)
            {
                for (int k1 = k - byte0; k1 <= k + byte0; k1++)
                {
                    if (k1 > k - byte0 && k1 < k + byte0 && j1 > j - byte0 && j1 < j + byte0 && i1 > i - byte0 && i1 < i + byte0 && flag)
                    {
                        l += absorbBlock(world, i1, j1, k1, byte1);
                        continue;
                    }

                    if (flag)
                    {
                        l += makeStill(world, i1, j1, k1, byte1);
                        continue;
                    }

                    if (!flag)
                    {
                        world.notifyBlocksOfNeighborChange(i1, j1, k1, blockID);
                    }
                }
            }
        }
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int i, Random random, int j)
    {
        return Block.sponge.blockID;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Block.sponge.blockID;
    }
    
    public void registerIcons(IconRegister iconRegister) 
    {
    	this.blockIcon = iconRegister.registerIcon(mod_Rediscovered.modid + ":" + texture);
    }
}
