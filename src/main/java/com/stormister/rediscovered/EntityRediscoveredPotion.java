package com.stormister.rediscovered;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityRediscoveredPotion extends EntityThrowable implements IEntityAdditionalSpawnData
{
    private ItemStack potionDamage;
    int color = 16388, randomTilt, metadata;
    PotionEffect potioneffect;
    
    public EntityRediscoveredPotion(World worldIn)
    {
        super(worldIn);
    }

    public EntityRediscoveredPotion(World worldIn, EntityLivingBase p_i1789_2_, int p_i1789_3_)
    {
        this(worldIn, p_i1789_2_, new ItemStack(mod_Rediscovered.RediscoveredPotion, 1, p_i1789_3_));
    }

    @SideOnly(Side.CLIENT)
    public EntityRediscoveredPotion(World worldIn, double p_i1791_2_, double p_i1791_4_, double p_i1791_6_, int p_i1791_8_)
    {
        this(worldIn, p_i1791_2_, p_i1791_4_, p_i1791_6_, new ItemStack(mod_Rediscovered.RediscoveredPotion, 1, p_i1791_8_));
    }

    public EntityRediscoveredPotion(World worldIn, double p_i1792_2_, double p_i1792_4_, double p_i1792_6_, ItemStack p_i1792_8_)
    {
        super(worldIn, p_i1792_2_, p_i1792_4_, p_i1792_6_);
        this.potionDamage = p_i1792_8_;
    }
    
    public EntityRediscoveredPotion(World worldIn, EntityLivingBase p_i1790_2_, ItemStack p_i1790_3_)
    {
        super(worldIn, p_i1790_2_);
        this.potionDamage = p_i1790_3_;
        randomTilt = rand.nextInt(360);
        this.metadata = potionDamage.getItemDamage();
        
        if(this.metadata == 100){
            potioneffect = new PotionEffect(9, 720, 0);
            color = 16388;
        }
        else if(this.metadata == 101){
        	potioneffect = new PotionEffect(15, 720, 0);
        	color = 16393;
        }
        else if(this.metadata == 102){
        	potioneffect = new PotionEffect(4, 720, 0);
        	color = 16398;
        }
        else{
        	potioneffect = new PotionEffect(0, 0, 0);
        	color = 0;
        }
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.05F;
    }

    protected float func_70182_d()
    {
        return 0.5F;
    }

    protected float func_70183_g()
    {
        return -20.0F;
    }
    
    public int getRandomTilt(){
    	return randomTilt;
    }
    
    /**
     * Sets the PotionEffect by the given id of the potion effect.
     */
    public void setPotionDamage(int potionId)
    {
        if (this.potionDamage == null)
        {
            this.potionDamage = new ItemStack(Items.potionitem, 1, 0);
        }

        this.potionDamage.setItemDamage(potionId);
    }
    
    public int getPotionDamage()
    {
        if (this.potionDamage == null)
        {
            this.potionDamage = new ItemStack(Items.potionitem, 1, 0);
        }
        return this.metadata;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition p_70184_1_)
    {
        if (!this.worldObj.isRemote)
        {
            
        	AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
                List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

                if (!list1.isEmpty())
                {
                    Iterator iterator = list1.iterator();

                    while (iterator.hasNext())
                    {
                        EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
                        double d0 = this.getDistanceSqToEntity(entitylivingbase);

                        if (d0 < 16.0D)
                        {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                            if (entitylivingbase == p_70184_1_.entityHit)
                            {
                                d1 = 1.0D;
                            }
                            
                            int i = potioneffect.getPotionID();
                            int j = (int)(d1 * (double)potioneffect.getDuration() + 0.5D);

                            if (j > 20)
                            {
                            	entitylivingbase.addPotionEffect(new PotionEffect(i, j, potioneffect.getAmplifier()));
                            }                            
                        }
                    }
                }
            
            this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), color);
            this.setDead();
        }
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);

        if (tagCompund.hasKey("Potion", 10))
        {
            this.potionDamage = ItemStack.loadItemStackFromNBT(tagCompund.getCompoundTag("Potion"));
        }
        else
        {
            this.setPotionDamage(tagCompund.getInteger("potionValue"));
        }

        if (this.potionDamage == null)
        {
            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);

        if (this.potionDamage != null)
        {
            tagCompound.setTag("Potion", this.potionDamage.writeToNBT(new NBTTagCompound()));
        }
    }

    public void writeSpawnData(ByteBuf buffer) {
    	buffer.writeInt(this.metadata);
    }

    public void readSpawnData(ByteBuf buffer) {
    	this.metadata = buffer.readInt();
    }
}