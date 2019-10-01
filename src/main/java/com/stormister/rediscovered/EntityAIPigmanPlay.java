package com.stormister.rediscovered;

import com.stormister.rediscovered.EntityPigman;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAIPigmanMate extends EntityAIBase {

	private EntityPigman villagerObj;
	private EntityPigman mate;
	private World worldObj;
	private int matingTimeout;
	Village villageObj;
	
	public EntityAIPigmanMate (EntityPigman pigmanIn){
		this.villagerObj = pigmanIn;
		this.worldObj = pigmanIn.worldObj;
		this.setMutexBits(8);
	}

	protected boolean isAIEnabled() {
		return true;
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.villagerObj.getGrowingAge() != 0) {
			return false;
		} else if (this.villagerObj.getRNG().nextInt(500) != 0) {
			return false;
		} else {
			this.villageObj = this.worldObj.getVillageCollection().getNearestVillage(new BlockPos(this.villagerObj), 0);

			if (this.villageObj == null) {
				return false;
			} else if (this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getIsWillingToMate(true)) {
				Entity entity = this.worldObj.findNearestEntityWithinAABB(EntityPigman.class,
						this.villagerObj.getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D), this.villagerObj);

				if (entity == null) {
					return false;
				} else {
					this.mate = (EntityPigman) entity;
					return this.mate.getGrowingAge() == 0 && this.mate.getIsWillingToMate(true);
				}
			} else {
				return false;
			}
		}
	}
	
	@Override
	public void startExecuting(){
		
	}
	
	@Override
	public boolean continueExecuting() {
		return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager()
				&& this.villagerObj.getGrowingAge() == 0 && this.villagerObj.getIsWillingToMate(false);
	}
		
	public void updateTask() {
		--this.matingTimeout;
		this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);

		if (this.villagerObj.getDistanceSqToEntity(this.mate) > 2.25D) {
			this.villagerObj.getNavigator().tryMoveToEntityLiving(this.mate, 0.25D);
		} else if (this.matingTimeout == 0 && this.mate.isMating()) {
			this.giveBirth();
		}

		if (this.villagerObj.getRNG().nextInt(35) == 0) {
			this.worldObj.setEntityState(this.villagerObj, (byte) 12);
		}
	}

	private boolean checkSufficientDoorsPresentForNewVillager() {
		if (!this.villageObj.isMatingSeason()) {
			return false;
		} else {
			int i = (int) ((double) ((float) this.villageObj.getNumVillageDoors()) * 0.35D);
			return this.villageObj.getNumVillagers() < i;
		}
	}

	private void giveBirth() {
		EntityPigman entityvillager = this.villagerObj.createChild(this.mate);
		this.mate.setGrowingAge(6000);
		this.villagerObj.setGrowingAge(6000);
		this.mate.setIsWillingToMate(false);
		this.villagerObj.setIsWillingToMate(false);
		entityvillager.setGrowingAge(-24000);
		entityvillager.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
		this.worldObj.spawnEntityInWorld(entityvillager);
		this.worldObj.setEntityState(entityvillager, (byte) 12);
	}
}
