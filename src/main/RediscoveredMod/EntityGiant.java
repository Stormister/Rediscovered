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
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGiant extends EntityMob
{
    public EntityGiant(World par1World)
    {
        super(par1World);
        this.yOffset *= 6.0F;
        stepHeight = 5.0F;
        this.setSize(this.width, this.height * 6.0F);
        this.experienceValue = 30;
    }

	protected void applyEntityAttributes()
	{
	    super.applyEntityAttributes();
	    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100.0D);
	    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
	    this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(50.0D);
	}
	
	/**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
        return this.worldObj.getLightBrightness(par1, par2, par3) - 0.5F;
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
            return Item.appleGold.itemID;
    }
    
    protected void dropRareDrop(int par1)
    {
        switch (rand.nextInt(4))
        {
            case 0:
                dropItem(Item.ingotGold.itemID, 1);
                break;

            case 1:
                dropItem(Item.diamond.itemID, 1);
                break;
                
            case 2:
            	this.entityDropItem(new ItemStack(Item.appleGold, 1, 1), 0.0F);
                break;

        }
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.boundingBox.minY);
            int k = MathHelper.floor_double(this.posZ);  
            return
              this.getBlockPathWeight(i, j, k) >= 0.0F &&
              this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() &&
              !this.worldObj.isAnyLiquid(this.boundingBox);
    }
    
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 3;
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 50;
    }
}
