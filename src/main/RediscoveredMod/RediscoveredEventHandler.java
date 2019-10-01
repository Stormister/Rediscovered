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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class RediscoveredEventHandler
{
	Random gen = new Random();
	protected static Random itemRand = new Random();
	
	
	//Sky Dimension Teleportation
		@ForgeSubscribe
		public void onPlayerSleepInBed(PlayerSleepInBedEvent event) 
		{
			EntityPlayer player = event.entityPlayer;
			InventoryPlayer inv = player.inventory;
			ItemStack itemStack = inv.getStackInSlot(inv.currentItem);
			World world = event.entityLiving.worldObj;
			if(!mod_Rediscovered.DreamBedEnabled && player.dimension == 0 && !world.isDaytime() && itemRand.nextInt(100)<=mod_Rediscovered.DreamChance && (itemStack == null || itemStack.getItem() != mod_Rediscovered.DreamPillow) && player instanceof EntityPlayerMP)
			{
				ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
				props.setRespawn(event.x, event.y, event.z);
				EntityPlayerMP thePlayer = (EntityPlayerMP) player;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_Rediscovered.DimID, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(mod_Rediscovered.DimID)));
			}
		}
		@ForgeSubscribe
		public void onLivingHurtEvent(LivingHurtEvent event) 
		{	
			if (!event.entityLiving.worldObj.isRemote && event.entityLiving instanceof EntityPlayerMP && event.entityLiving.dimension == mod_Rediscovered.DimID && event.source.damageType.equals("outOfWorld")) 
			{
				final WorldServer world = (WorldServer) event.entityLiving.worldObj;
				final Object[] entityList = world.loadedEntityList.toArray();
					for (final Object o : entityList)
					{
						if (o instanceof EntityPlayerMP)
						{
							final EntityPlayerMP e = (EntityPlayerMP) o;

							if (e.posY <= 10)
							{
								ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
								event.setCanceled(true);
								e.mcServer.getConfigurationManager().transferPlayerToDimension(e, 0, new SkyDimensionTeleporter(e.mcServer.worldServerForDimension(0)));
								ChunkCoordinates coordinates = props.getRespawn();
				        		if(coordinates!=null)	
				        			coordinates = this.verifyRespawnCoordinates(e.mcServer.worldServerForDimension(0), coordinates, true, e);
				        		if(coordinates == null) {
				                	coordinates = world.getSpawnPoint();
				            		e.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
				            		e.addChatMessage("Your home bed was missing or obstructed so you sleep walked all the way to spawn!");
				        		}
				        		else if(coordinates != null) {
				            		e.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
				                }
								e.fallDistance = 0;
							}
						
						}
					}
			}
		}
		@ForgeSubscribe
		public void onPlayerInteract(PlayerInteractEvent event) 
		{
			EntityPlayer player = event.entityPlayer;
			InventoryPlayer inv = player.inventory;
			ItemStack itemStack = inv.getStackInSlot(inv.currentItem);
			final World world = (World) event.entityLiving.worldObj;

			if(!event.entityLiving.worldObj.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK && world.getBlockId(event.x, event.y, event.z) == Block.bed.blockID && !mod_Rediscovered.DreamBedEnabled && ((itemStack != null && itemStack.getItem() == mod_Rediscovered.DreamPillow && player.dimension == 0 && (mod_Rediscovered.DaytimeBed || (!mod_Rediscovered.DaytimeBed && !world.isDaytime()))) || (player.dimension == mod_Rediscovered.DimID && !mod_Rediscovered.DreamBedEnabled)) && player instanceof EntityPlayerMP){
	        	event.setCanceled(true);
				EntityPlayerMP thePlayer = (EntityPlayerMP) player;
				ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
				if (player.dimension == 0)
	        	{            
					props.setRespawn(event.x, event.y, event.z);
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_Rediscovered.DimID, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(mod_Rediscovered.DimID)));
	        	}
	        	else if (player.dimension == mod_Rediscovered.DimID)
	        	{
	        		thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
	        		ChunkCoordinates coordinates = props.getRespawn();
	        		if(coordinates!=null)	
	        			coordinates = this.verifyRespawnCoordinates(thePlayer.mcServer.worldServerForDimension(0), coordinates, true, thePlayer);
	        		if(coordinates == null) {
	                	coordinates = world.getSpawnPoint();
	            		thePlayer.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
	            		player.addChatMessage("Your home bed was missing or obstructed so you sleep walked all the way to spawn!");
	        		}
	        		else if(coordinates != null) {
	            		thePlayer.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
	                }

	        	}
			}
		}
		
		
		//Mob AI
		@ForgeSubscribe
		public void onEntityJoinWorld(EntityJoinWorldEvent event) 
		{
			if(event.entity instanceof EntityZombie){
				EntityCreature entity = (EntityCreature) event.entity;
				if(mod_Rediscovered.ScarecrowAttractsMobs){
					entity.targetTasks.addTask(0, new EntityAINearestAttackableTarget(entity, EntityScarecrow.class, 0, true));
					entity.tasks.addTask(0, new EntityAIAttackOnCollide(entity, EntityScarecrow.class, 0.8D, false));
				}
				else
					entity.tasks.addTask(0, new EntityAIAvoidEntity(entity, EntityScarecrow.class, 8.0F, 1.0D, 0.8D));
			}
			if(event.entity instanceof EntityAnimal){
				EntityCreature entity = (EntityCreature) event.entity;
				entity.tasks.addTask(0, new EntityAIAvoidEntity(entity, EntityScarecrow.class, 8.0F, 1.0D, 0.8D));
			}
		}
	
	@ForgeSubscribe
	public void onBonemealClick(BonemealEvent event) 
	{
		if (event.ID == mod_Rediscovered.CherrySapling.blockID) 
		{
			event.setResult(Result.ALLOW);
			//if(itemRand.nextInt(2) == 0)
			//{
				//grow tree
				((BlockCherrySapling)mod_Rediscovered.CherrySapling).markOrGrowMarked(event.world, event.X, event.Y, event.Z, event.world.rand);
				
			//}
		}
	}
	
	@ForgeSubscribe
    public void onArrowNockEvent(ArrowNockEvent event)
    {
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack par1ItemStack = inv.getStackInSlot(inv.currentItem);
		if(inv.getCurrentItem().itemID == Item.bow.itemID)
		{
			if(mod_Rediscovered.QuickBow)
	        {
		        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		        ItemStack itemstack = player.inventory.armorInventory[2];
		
		        if (itemstack != null && itemstack.itemID == mod_Rediscovered.Quiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.LeatherQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.ChainQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.IronQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.GoldQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.DiamondQuiver.itemID)
		        {
		            if (player.inventory.hasItem(Item.arrow.itemID) || flag)
		            {
		                EntityArrow entityarrow = new EntityArrow(player.worldObj, player, 1.0F);
		                player.worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		                entityarrow.setIsCritical(true);
		
		                
		
		                if (!player.worldObj.isRemote)
		                {
		                	if (!flag)
			                {
			                	player.inventory.consumeInventoryItem(Item.arrow.itemID);
			                }
			                else
			                {
			                    entityarrow.canBePickedUp = 0;
			                }
		                	player.worldObj.spawnEntityInWorld(entityarrow);
		                }
		                event.setCanceled(true);
		            }
		        }
	    	}
		}
    }
	
	@ForgeSubscribe
    public void onArrowLooseEvent(ArrowLooseEvent event)
    {
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack par1ItemStack = inv.getStackInSlot(inv.currentItem);
		if(inv.getCurrentItem().itemID == Item.bow.itemID)
		{
			if(mod_Rediscovered.QuickBow)
	        {
		        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		        ItemStack itemstack = player.inventory.armorInventory[2];
		
		        if (itemstack != null && itemstack.itemID == mod_Rediscovered.Quiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.LeatherQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.ChainQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.IronQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.GoldQuiver.itemID || itemstack != null && itemstack.itemID == mod_Rediscovered.DiamondQuiver.itemID)
		        {
		        	event.setCanceled(true);
		        }
	        }
		}
    }
    
	
    @ForgeSubscribe
    public void onPlayerMove(LivingUpdateEvent e)
    {
    	if (e.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer p = (EntityPlayer) e.entityLiving;
            
            if (!p.worldObj.isRemote)
            {
                if (mod_Rediscovered.hasLitLanternOnHotbar(p.inventory))
                {
                    Pos3 pos = new Pos3(MathHelper.floor_double(p.posX), MathHelper.floor_double(p.posY) + 1, MathHelper.floor_double(p.posZ));

                    if (p.ridingEntity != null)
                    {
                        pos = new Pos3(MathHelper.floor_double(p.ridingEntity.posX), MathHelper.floor_double(p.ridingEntity.posY) + 1, MathHelper.floor_double(p.ridingEntity.posZ));
                    }

                    if (p.worldObj.getBlockId(pos.x, pos.y, pos.z) == 0)
                    {
                        p.worldObj.setBlock(pos.x, pos.y, pos.z, mod_Rediscovered.Lantern.blockID);
                    }

                    if (mod_Rediscovered.usernameLastPosMap.containsKey(p.username))
                    {
                        Pos3 pos2 = mod_Rediscovered.usernameLastPosMap.get(p.username);

                        if ((pos2.x != pos.x || pos2.y != pos.y || pos2.z != pos.z) && p.worldObj.getBlockId(pos2.x, pos2.y, pos2.z) == mod_Rediscovered.Lantern.blockID)
                        {
                            p.worldObj.setBlock(pos2.x, pos2.y, pos2.z, 0);
                        }
                    }

                    mod_Rediscovered.usernameLastPosMap.put(p.username, pos);
                }
                else
                {
                    if (mod_Rediscovered.usernameLastPosMap.containsKey(p.username))
                    {
                        Pos3 pos = mod_Rediscovered.usernameLastPosMap.get(p.username);

                        if (p.worldObj.getBlockId(pos.x, pos.y, pos.z) == mod_Rediscovered.Lantern.blockID)
                        {
                            p.worldObj.setBlock(pos.x, pos.y, pos.z, 0);
                        }

                        mod_Rediscovered.usernameLastPosMap.remove(p.username);
                    }
                }
            }
        }
    }
    
    
    
    @ForgeSubscribe
    public void onEntityConstructing(EntityConstructing event)
    {
	    if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		    ExtendedPlayer.register((EntityPlayer) event.entity);
    }
    
    public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates, boolean par2, EntityPlayerMP player)
    {
//    	player.addChatComponentMessage(new ChatComponentText("" + player.mcServer.worldServerForDimension(0).getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)));
        if (player.mcServer.worldServerForDimension(0).getBlockId(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)== Block.bed.blockID || (mod_Rediscovered.DreamBedEnabled && player.mcServer.worldServerForDimension(0).getBlockId(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ) == mod_Rediscovered.DreamBed.blockID))
        {
            return par1ChunkCoordinates;
        }
        else
        {
            return null;
        }
    }
}
