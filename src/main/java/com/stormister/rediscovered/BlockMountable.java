package com.stormister.rediscovered;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/*ITOS:
 *This class is for your convenience. It is not necessary for your block to be a subclass of this class. 
 *As long as your block has and/or calls the methods specified here it can use the EntityMountableBlock 
 *class. If your class is a subclass to this class you don't have to do anything to be able to mount it, 
 *just make sure that the onBlockActivated methods are not overridden.
*/

public class BlockMountable extends Block
{
	
	//This constructor just pass thing on.
	public BlockMountable(Material material)
	{
		super(material);
	}
	
	//Use this method for the default mounting position.
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
		return onBlockActivated(world, i, j, k, entityplayer, 0.5F, 0.5F, 0.5F, 0, 0, 0, 0);
    }
	
	//Use this method for a custom mounting height.
	public static boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float y)
    {
		return onBlockActivated(world, i, j, k, entityplayer, 0.5F, 0.5F, 0.5F, 0, 0, 0, 0);
    }
    
	//This is the main onBlockActivated method. Use it for fully custom mounting positions.
	public static boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float x, float y, float z, int north, int south, int east, int west)
    {
		if (!world.isRemote)
		{
			//Looks for EMBs up to 1 block away from the activated block. Hopefully you didn't set the mounting position further away than this.
			List<EntityMountableBlock> listEMB = world.getEntitiesWithinAABB(EntityMountableBlock.class, AxisAlignedBB.getBoundingBox(i, j, k, i + 1.0D, j + 1.0D, k + 1.0D).expand(1D, 1D, 1D));
	    	for (EntityMountableBlock entitytocheck : listEMB)
	    	{
	    		//Looks for an EMB created by this block.
	    		if (entitytocheck.orgBlockPosX == i && entitytocheck.orgBlockPosY == j && entitytocheck.orgBlockPosZ == k)
	    		{
	    			entitytocheck.interact(entityplayer);
	    			return true;
	    		}
	    	}
			//Sets coordinates for mounting a north oriented block.
			float mountingX = i + x;
			float mountingY = j + y;
			float mountingZ = k + z;
			//Changes coordinates for mounting to compensate for none-north block orientation.
			if(north != south) 
			{
				int md = world.getBlockMetadata(i, j, k);
				if (md == east) 
				{
					mountingX = i + 1 - z; 
					mountingZ = k + x; 
				}
				else if (md == south) 
				{
					mountingX = i + 1 - x; 
					mountingZ = k + 1 - z; 
				}
				else if (md == west) 
				{
					mountingX = i + z; 
					mountingZ = k + 1 - x; 
				}
			}
	    	//Creates a new EMB if none had been created already or if the old one was bugged.
	    	EntityMountableBlock nemb = new EntityMountableBlock(world, entityplayer, i, j, k, mountingX, mountingY, mountingZ); 
	    	world.spawnEntityInWorld(nemb);
	    	nemb.interact(entityplayer);
	    	return true;
		}
		return true;
    }
	
}