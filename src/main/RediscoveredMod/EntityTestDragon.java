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

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityTestDragon extends EntityDragon implements IEntityMultiPartRed
{
	public double targetX;
    public double targetY;
    public double targetZ;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    
    public float x2;
    public float z2;
    
	public float wingSpeed;
	
    /** Force selecting a new flight target at next tick if set to true. */
    public boolean forceNewTarget = false;
    
    private Entity target;
    
	/**
     * Ring buffer array for the last 64 Y-positions and yaw rotations. Used to calculate offsets for the animations.
     */
    public double[][] ringBuffer = new double[64][3];
	
	/**
     * Index into the ring buffer. Incremented once per tick and restarts at 0 once it reaches the end of the buffer.
     */
    public int ringBufferIndex = -1;
    
    public boolean slowed = false;
    public boolean angry = false;
    
    /** Animation time at previous tick. */
    public float prevAnimTime = 0.0F;

    /**
     * Animation time, used to control the speed of the animation cycles (wings flapping, jaw opening, etc.)
     */
    public float animTime = 0.0F;
    public int deathTicks = 0;
    
    public int health = 10;
    
    /** An array containing all body parts of this dragon */
    public EntityGoodDragonPart[] dragonPartArray;
    public EntityGoodDragonPart dragonPartTail1;
    public EntityGoodDragonPart dragonPartTail2;
    public EntityGoodDragonPart dragonPartTail3;

    public EntityTestDragon(World par1World)
    {
        super(par1World);
        this.setHealth(this.getMaxHealth());
        wingSpeed = 0.2F;
        this.dragonPartArray = new EntityGoodDragonPart[] {this.dragonPartTail1 = new EntityGoodDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityGoodDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityGoodDragonPart(this, "tail", 4.0F, 4.0F)};
        this.getNavigator().setAvoidsWater(true);
        this.setSize(this.width * 6.0F, this.height * 1.5F);
        this.isImmuneToFire = true;
        this.targetY = 10.0D;
        this.ignoreFrustumCheck = true;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(200.0D);
    }


    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(20, new Byte((byte)0));
    }
    
    /**
	 * Returns a double[3] array with movement offsets, used to calculate trailing tail/neck positions. [0] = yaw
	 * offset, [1] = y offset, [2] = unused, always 0. Parameters: buffer index offset, partial ticks.
	 */
	public double[] getMovementOffsets(int par1, float par2)
	{
		if (this.health <= 0)
		{
			par2 = 0.0F;
		}

		par2 = 1.0F - par2;
		int var3 = this.ringBufferIndex - par1 * 1 & 63;
		int var4 = this.ringBufferIndex - par1 * 1 - 1 & 63;
		double[] var5 = new double[3];
		double var6 = this.ringBuffer[var3][0];
		double var8 = MathHelper.wrapAngleTo180_double(this.ringBuffer[var4][0] - var6);
		var5[0] = var6 + var8 * (double)par2;
		var6 = this.ringBuffer[var3][1];
		var8 = this.ringBuffer[var4][1] - var6;
		var5[1] = var6 + var8 * (double)par2;
		var5[2] = this.ringBuffer[var3][2] + (this.ringBuffer[var4][2] - this.ringBuffer[var3][2]) * (double)par2;
		return var5;
	}

	/**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        float f;
        float f1;
        field_756_e = field_752_b;

        if (this.worldObj.isRemote)
        {
            f = MathHelper.cos(this.animTime * (float)Math.PI * 2.0F);
            f1 = MathHelper.cos(this.prevAnimTime * (float)Math.PI * 2.0F);

            if (f1 <= -0.3F && f >= -0.3F)
            {
                this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
            }
        }

        this.prevAnimTime = this.animTime;
        
        if (!onGround && field_755_h < 1.0F)
        {
            field_755_h = 1.0F;
        }

        field_755_h *= 0.90000000000000002D;
        field_752_b += field_755_h * wingSpeed;
        float f2;

        if (this.getHealth() <= 0.0F)
        {
            f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle("largeexplode", this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            f = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
            f *= (float)Math.pow(2.0D, this.motionY);

            if (this.slowed)
            {
                this.animTime += f * 0.5F;
            }
            else
            {
                this.animTime += f;
            }

            this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

            if (this.ringBufferIndex < 0)
            {
                for (int i = 0; i < this.ringBuffer.length; ++i)
                {
                    this.ringBuffer[i][0] = (double)this.rotationYaw;
                    this.ringBuffer[i][1] = this.posY;
                }
            }

            if (++this.ringBufferIndex == this.ringBuffer.length)
            {
                this.ringBufferIndex = 0;
            }

            this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
            this.ringBuffer[this.ringBufferIndex][1] = this.posY;
            double d0;
            double d1;
            double d2;
            double d3;
            float f3;

            if (this.worldObj.isRemote)
            {
                if (this.newPosRotationIncrements > 0)
                {
                    d3 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
                    d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
                    d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
                    d2 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
                    this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
                    this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
                    --this.newPosRotationIncrements;
                    this.setPosition(d3, d0, d1);
                    this.setRotation(this.rotationYaw, this.rotationPitch);
                }
            }
            else
            {
                d3 = this.targetX - this.posX;
                d0 = this.targetY - this.posY;
                d1 = this.targetZ - this.posZ;
                d2 = d3 * d3 + d0 * d0 + d1 * d1;

                if (this.target != null)
                {
                    this.targetX = this.target.posX;
                    this.targetZ = this.target.posZ;
                    double d4 = this.targetX - this.posX;
                    double d5 = this.targetZ - this.posZ;
                    double d6 = Math.sqrt(d4 * d4 + d5 * d5);
                    double d7 = 0.4000000059604645D + d6 / 80.0D - 1.0D;

                    if (d7 > 10.0D)
                    {
                        d7 = 10.0D;
                    }

                    this.targetY = this.target.boundingBox.minY + d7;
                }
                else
                {
                    this.targetX += this.rand.nextGaussian() * 2.0D;
                    this.targetZ += this.rand.nextGaussian() * 2.0D;
                }

                if (this.forceNewTarget || d2 < 100.0D || d2 > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
                {
                    this.setNewTarget();
                }

                d0 /= (double)MathHelper.sqrt_double(d3 * d3 + d1 * d1);
                f3 = 0.6F;

                if (d0 < (double)(-f3))
                {
                    d0 = (double)(-f3);
                }

                if (d0 > (double)f3)
                {
                    d0 = (double)f3;
                }

                this.motionY += d0 * 0.10000000149011612D;
                this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
                double d8 = 180.0D - Math.atan2(d3, d1) * 180.0D / Math.PI;
                double d9 = MathHelper.wrapAngleTo180_double(d8 - (double)this.rotationYaw);

                if (d9 > 50.0D)
                {
                    d9 = 50.0D;
                }

                if (d9 < -50.0D)
                {
                    d9 = -50.0D;
                }

                Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
                Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool((double)MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F))).normalize();
                float f4 = (float)(vec31.dotProduct(vec3) + 0.5D) / 1.5F;

                if (f4 < 0.0F)
                {
                    f4 = 0.0F;
                }

                this.randomYawVelocity *= 0.8F;
                float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
                double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;

                if (d10 > 40.0D)
                {
                    d10 = 40.0D;
                }

                this.randomYawVelocity = (float)((double)this.randomYawVelocity + d9 * (0.699999988079071D / d10 / (double)f5));
                this.rotationYaw += this.randomYawVelocity * 0.1F;
                float f6 = (float)(2.0D / (d10 + 1.0D));
                float f7 = 0.06F;
                this.moveFlying(0.0F, -1.0F, f7 * (f4 * f6 + (1.0F - f6)));

                if (this.slowed)
                {
                    this.moveEntity(this.motionX * -0.800000011920929D, this.motionY * 0.800000011920929D, this.motionZ * -0.800000011920929D);
                }
                else
                {
                    this.moveEntity(-this.motionX, this.motionY, -this.motionZ);
                }

                Vec3 vec32 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.motionX, this.motionY, this.motionZ).normalize();
                float f8 = (float)(vec32.dotProduct(vec31) + 1.0D) / 2.0F;
                f8 = 0.8F + 0.15F * f8;
                this.motionX *= (double)f8;
                this.motionZ *= (double)f8;
                this.motionY *= 0.9100000262260437D;
            }

            this.renderYawOffset = this.rotationYaw;
            this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
            this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
            this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
            this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
            this.dragonPartBody.height = 3.0F;
            this.dragonPartBody.width = 5.0F;
            this.dragonPartWing1.height = 2.0F;
            this.dragonPartWing1.width = 4.0F;
            this.dragonPartWing2.height = 3.0F;
            this.dragonPartWing2.width = 4.0F;
            f1 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * (float)Math.PI;
            f2 = MathHelper.cos(f1);
            float f9 = -MathHelper.sin(f1);
            float f10 = this.rotationYaw * (float)Math.PI / 180.0F;
            float f11 = MathHelper.sin(f10);
            float f12 = MathHelper.cos(f10);
            this.dragonPartBody.onUpdate();
            this.dragonPartBody.setLocationAndAngles(this.posX + (double)(f11 * 0.5F), this.posY, this.posZ - (double)(f12 * 0.5F), 0.0F, 0.0F);
            this.dragonPartWing1.onUpdate();
            this.dragonPartWing1.setLocationAndAngles(this.posX + (double)(f12 * 4.5F), this.posY + 2.0D, this.posZ + (double)(f11 * 4.5F), 0.0F, 0.0F);
            this.dragonPartWing2.onUpdate();
            this.dragonPartWing2.setLocationAndAngles(this.posX - (double)(f12 * 4.5F), this.posY + 2.0D, this.posZ - (double)(f11 * 4.5F), 0.0F, 0.0F);

            if (!this.worldObj.isRemote && this.hurtTime == 0)
            {
                this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                this.attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.boundingBox.expand(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble = this.getMovementOffsets(5, 1.0F);
            double[] adouble1 = this.getMovementOffsets(0, 1.0F);
            f3 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            float f13 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            this.dragonPartHead.onUpdate();
            this.dragonPartHead.setLocationAndAngles(this.posX + (double)(f3 * 5.5F * f2), this.posY + (adouble1[1] - adouble[1]) * 1.0D + (double)(f9 * 5.5F), this.posZ - (double)(f13 * 5.5F * f2), 0.0F, 0.0F);

            for (int j = 0; j < 3; ++j)
            {
                EntityGoodDragonPart entitydragonpart = null;

                if (j == 0)
                {
                    entitydragonpart = this.dragonPartTail1;
                }

                if (j == 1)
                {
                    entitydragonpart = this.dragonPartTail2;
                }

                if (j == 2)
                {
                    entitydragonpart = this.dragonPartTail3;
                }

                double[] adouble2 = this.getMovementOffsets(12 + j * 2, 1.0F);
                float f14 = this.rotationYaw * (float)Math.PI / 180.0F + this.simplifyAngle(adouble2[0] - adouble[0]) * (float)Math.PI / 180.0F * 1.0F;
                float f15 = MathHelper.sin(f14);
                float f16 = MathHelper.cos(f14);
                float f17 = 1.5F;
                float f18 = (float)(j + 1) * 2.0F;
                entitydragonpart.onUpdate();
                entitydragonpart.setLocationAndAngles(this.posX - (double)((f11 * f17 + f15 * f18) * f2), this.posY + (adouble2[1] - adouble[1]) * 1.0D - (double)((f18 + f17) * f9) + 1.5D, this.posZ + (double)((f12 * f17 + f16 * f18) * f2), 0.0F, 0.0F);
            }
        }
    }
	
	public float getY2()
	{
		float y = (float)(this.posY + 2.0D);
		return y;
	}
	
	public float getZ2()
	{
		float var5 = this.rotationYaw * (float)Math.PI / 180.0F;
		float var27 = MathHelper.sin(var5);
		float z = (float)(this.posZ + (var27 * 4.5F));
		return z;
	}
	
	public float getX2()
	{
		float var5 = this.rotationYaw * (float)Math.PI / 180.0F;
		float var7 = MathHelper.cos(var5);
		float x = (float) (this.posX + (var7 * 4.5F));
		return x;
	}

    /**
	 * Pushes all entities inside the list away from the enderdragon.
	 */
	private void collideWithEntities(List par1List)
	{
		double var2 = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
		double var4 = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;
		Iterator var6 = par1List.iterator();

		while (var6.hasNext())
		{
			Entity var7 = (Entity)var6.next();

			if (var7 instanceof EntityLiving)
			{
				double var8 = var7.posX - var2;
				double var10 = var7.posZ - var4;
				double var12 = var8 * var8 + var10 * var10;
				var7.addVelocity(var8 / var12 * 4.0D, 0.20000000298023224D, var10 / var12 * 4.0D);
			}
		}
	}

    /**
     * Attacks all entities inside this list, dealing 5 hearts of damage.
     */
    private void attackEntitiesInList(List par1List)
    {
    	if(angry)
    	{
        	for (int var2 = 0; var2 < par1List.size(); ++var2)
        	{
        		Entity var3 = (Entity)par1List.get(var2);

            	if (var3 instanceof EntityLiving || var3 instanceof EntityPlayer)
            	{
            		var3.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
            	}
        	}
    	}
    }

    /**
     * Sets a new target for the flight AI. It can be a random coordinate or a nearby player.
     */
    private void setNewTarget()
    {
        this.forceNewTarget = false;

        
            boolean var1 = false;

            do
            {
                this.targetX = this.posX;
                this.targetY = (double)(70.0F + this.rand.nextFloat() * 50.0F);
                this.targetZ = this.posZ;
                this.targetX += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                this.targetZ += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
                double var2 = this.posX - this.targetX;
                double var4 = this.posY - this.targetY;
                double var6 = this.posZ - this.targetZ;
                var1 = var2 * var2 + var4 * var4 + var6 * var6 > 100.0D;
            }
            while (!var1);

            this.target = null;
        
    }

    /**
     * Simplifies the value of a number by adding/subtracting 180 to the point that the number is between -180 and 180.
     */
    private float simplifyAngle(double par1)
    {
        return (float)MathHelper.wrapAngleTo180_double(par1);
    }
    
    protected boolean func_82195_e(DamageSource par1DamageSource, int par2)
    {
        return super.attackEntityFrom(par1DamageSource, par2);
    }
    
    public boolean attackEntityFromPart(EntityGoodDragonPart par1EntityGoodDragonPart, DamageSource par2DamageSource, float par3)
	{
		
			par3 = par3 / 4 + 1;
		

		float var4 = this.rotationYaw * (float)Math.PI / 180.0F;
		float var5 = MathHelper.sin(var4);
		float var6 = MathHelper.cos(var4);
		this.targetX = this.posX + (double)(var5 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
		this.targetY = this.posY + (double)(this.rand.nextFloat() * 3.0F) + 1.0D;
		this.targetZ = this.posZ - (double)(var6 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
		this.target = null;

		if (par2DamageSource.getEntity() instanceof EntityPlayer || par2DamageSource == DamageSource.generic)
		{
			angry = true;
			this.func_82195_e(par2DamageSource, par3);
		}
		
		return true;
	}

    /**
     * handles entity death timer, experience orb and particle creation
     */
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)var1, this.posY + 2.0D + (double)var2, this.posZ + (double)var3, 0.0D, 0.0D, 0.0D);
        }

        int var4;
        int var5;

        if (!this.worldObj.isRemote)
        {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0)
            {
                var4 = 1000;

                while (var4 > 0)
                {
                    var5 = EntityXPOrb.getXPSplit(var4);
                    var4 -= var5;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
                }
            }

            if (this.deathTicks == 1)
            {
                this.worldObj.func_82739_e(1018, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
        }

        this.moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        this.renderYawOffset = this.rotationYaw += 20.0F;

        if (this.deathTicks == 200 && !this.worldObj.isRemote)
        {
            var4 = 2000;

            while (var4 > 0)
            {
                var5 = EntityXPOrb.getXPSplit(var4);
                var4 -= var5;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }

            this.createEnderPortal(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
            this.setDead();
        }
    }

    /**
     * Creates the ender portal leading back to the normal world after defeating the enderdragon.
     */
    private void createEnderPortal(int par1, int par2)
    {
        byte var3 = 64;
        byte var4 = 4;

        for (int var5 = var3 - 1; var5 <= var3 + 32; ++var5)
        {
            for (int var6 = par1 - var4; var6 <= par1 + var4; ++var6)
            {
                for (int var7 = par2 - var4; var7 <= par2 + var4; ++var7)
                {
                    double var8 = (double)(var6 - par1);
                    double var10 = (double)(var7 - par2);
                    double var12 = var8 * var8 + var10 * var10;

                    if (var12 <= ((double)var4 - 0.5D) * ((double)var4 - 0.5D))
                    {
                        if (var5 < var3)
                        {
                            if (var12 <= ((double)(var4 - 1) - 0.5D) * ((double)(var4 - 1) - 0.5D))
                            {
                                this.worldObj.setBlock(var6, var5, var7, mod_Rediscovered.CryingObsidian.blockID);
                            }
                        }
                        else if (var5 > var3)
                        {
                            this.worldObj.setBlock(var6, var5, var7, 0);
                        }
                        else if (var12 > ((double)(var4 - 1) - 0.5D) * ((double)(var4 - 1) - 0.5D))
                        {
                            this.worldObj.setBlock(var6, var5, var7, mod_Rediscovered.CryingObsidian.blockID);
                        }
                        else
                        {
                            this.worldObj.setBlock(var6, var5, var7, Block.fire.blockID);
                        }
                    }
                }
            }
        }

        this.worldObj.setBlock(par1, var3 + 0, par2, mod_Rediscovered.CryingObsidian.blockID);
        this.worldObj.setBlock(par1, var3 + 1, par2, mod_Rediscovered.CryingObsidian.blockID);
        this.worldObj.setBlock(par1, var3 + 2, par2, mod_Rediscovered.CryingObsidian.blockID);
        this.worldObj.setBlock(par1, var3 + 3, par2, mod_Rediscovered.CryingObsidian.blockID);
        this.worldObj.setBlock(par1, var3 + 4, par2, mod_Rediscovered.DragonEggRed.blockID);
    }

    /**
     * Makes the entity despawn if requirements are reached
     */
    protected void despawnEntity() {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.pig.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.pig.step", 0.15F, 1.0F);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return mod_Rediscovered.DragonEggRed.blockID;
    }

    

	@Override
	public World func_82194_d() 
	{
		return this.worldObj;
	}
}
