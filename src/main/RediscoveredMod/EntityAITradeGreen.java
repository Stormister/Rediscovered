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

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntityAITradeGreen extends EntityAIBase
{
    private EntityGreenVillager field_75276_a;

    public EntityAITradeGreen(EntityGreenVillager par1EntityVillager)
    {
        this.field_75276_a = par1EntityVillager;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.field_75276_a.isEntityAlive())
        {
            return false;
        }
        else if (this.field_75276_a.isInWater())
        {
            return false;
        }
        else if (!this.field_75276_a.onGround)
        {
            return false;
        }
        else if (this.field_75276_a.velocityChanged)
        {
            return false;
        }
        else
        {
            EntityPlayer var1 = this.field_75276_a.getCustomer();
            return var1 == null ? false : (this.field_75276_a.getDistanceSqToEntity(var1) > 16.0D ? false : var1.openContainer instanceof Container);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.field_75276_a.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.field_75276_a.setCustomer((EntityPlayer)null);
    }
}
