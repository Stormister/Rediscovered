package com.stormister.rediscovered;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.FakePlayer;
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
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class RediscoveredEventHandler
{
	Random gen = new Random();
	protected static Random itemRand = new Random();
	
	
	//Sky Dimension Teleportation
	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event) 
	{
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack itemStack = inv.getStackInSlot(inv.currentItem);
		World world = event.entityLiving.worldObj;
		if(player.dimension == 0 && !world.isDaytime() && itemRand.nextInt(100)<=mod_Rediscovered.DreamChance && (itemStack == null || itemStack.getItem() != mod_Rediscovered.DreamPillow) && player instanceof EntityPlayerMP)
		{
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);
			props.setRespawn(event.x, event.y, event.z);
			EntityPlayerMP thePlayer = (EntityPlayerMP) player;
			thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_Rediscovered.DimID, new SkyDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(mod_Rediscovered.DimID)));
		}
	}
	@SubscribeEvent
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
			            		e.addChatComponentMessage(new ChatComponentTranslation("message.missingbed", new Object[0]));
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
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) 
	{
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack itemStack = inv.getStackInSlot(inv.currentItem);
		final World world = (World) event.entityLiving.worldObj;

		if(!event.entityLiving.worldObj.isRemote && event.action == event.action.RIGHT_CLICK_BLOCK && world.getBlock(event.x, event.y, event.z) == Blocks.bed && ((itemStack != null && itemStack.getItem() == mod_Rediscovered.DreamPillow && player.dimension == 0 && (mod_Rediscovered.DaytimeBed || (!mod_Rediscovered.DaytimeBed && !world.isDaytime()))) || (player.dimension == mod_Rediscovered.DimID)) && player instanceof EntityPlayerMP){
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
            		player.addChatComponentMessage(new ChatComponentTranslation("message.missingbed", new Object[0]));
        		}
        		else if(coordinates != null) {
            		thePlayer.setPositionAndUpdate((double) coordinates.posX + 0.5D, (double) coordinates.posY + 3, (double) coordinates.posZ + 0.5D);
                }

        	}
		}
		
		
		//Bush Shearing
		if(event.action == event.action.RIGHT_CLICK_BLOCK && world.getBlock(event.x, event.y, event.z) == Blocks.double_plant && itemStack != null && itemStack.getItem() == Items.shears && player instanceof EntityPlayerMP){
        	event.setCanceled(true);
        	if(world.getBlockMetadata(event.x, event.y, event.z) == 4 || (world.getBlockMetadata(event.x, event.y-1, event.z) == 4)){
        		if(world.getBlockMetadata(event.x, event.y, event.z) == 4){
        			world.setBlock(event.x, event.y, event.z, mod_Rediscovered.EmptyRoseBush);
        			world.setBlock(event.x, event.y+1, event.z, mod_Rediscovered.EmptyRoseBushTop);
        		}
        		else if((world.getBlockMetadata(event.x, event.y-1, event.z) == 4)){
        			world.setBlock(event.x, event.y-1, event.z, mod_Rediscovered.EmptyRoseBush);
        			world.setBlock(event.x, event.y, event.z, mod_Rediscovered.EmptyRoseBushTop);
        		}
        		ItemStack itemStack2 = new ItemStack(mod_Rediscovered.Rose, world.rand.nextInt(3)+1);
        		EntityItem item = new EntityItem(world, event.x, event.y, event.z, itemStack2);
        		world.spawnEntityInWorld(item);
        	}
        	if(world.getBlockMetadata(event.x, event.y, event.z) == 5 || (world.getBlockMetadata(event.x, event.y-1, event.z) == 5)){
        		if(world.getBlockMetadata(event.x, event.y, event.z) == 5){
        			world.setBlock(event.x, event.y, event.z, mod_Rediscovered.EmptyPeonyBush);
        			world.setBlock(event.x, event.y+1, event.z, mod_Rediscovered.EmptyPeonyBushTop);
        		}
        		else if((world.getBlockMetadata(event.x, event.y-1, event.z) == 5)){
        			world.setBlock(event.x, event.y-1, event.z, mod_Rediscovered.EmptyPeonyBush);
        			world.setBlock(event.x, event.y, event.z, mod_Rediscovered.EmptyPeonyBushTop);
        		}
        		ItemStack itemStack2 = new ItemStack(mod_Rediscovered.Peony, world.rand.nextInt(3)+1);
        		EntityItem item = new EntityItem(world, event.x, event.y, event.z, itemStack2);
        		world.spawnEntityInWorld(item);
        	}
        	itemStack.damageItem(1, player);
		}
	}
	
	
	//Mob AI
	@SubscribeEvent
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
	
	
	//Cherry Tree Sapling
	@SubscribeEvent
	public void onBonemealClick(BonemealEvent event) 
	{
		World world = event.world;
		if (event.block.equals(mod_Rediscovered.CherrySapling)) 
		{
			event.setResult(Result.ALLOW);
			if (!world.isRemote)
			{
				double chance = 0.45D;
				if (world.rand.nextFloat() < chance)
				{
					//grow tree
					((BlockCherrySapling)mod_Rediscovered.CherrySapling).func_149878_d(event.world, event.x, event.y, event.z, event.world.rand);
					
				}
			}
		}
	}
	
	
	//Quiver Bow
	@SubscribeEvent
    public void onArrowNockEvent(ArrowNockEvent event)
    {
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack par1ItemStack = inv.getStackInSlot(inv.currentItem);
			
		        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		        ItemStack itemstack = player.inventory.armorInventory[2];

		        if(itemstack != null && (itemstack.getItem() == mod_Rediscovered.Quiver || itemstack.getItem() == mod_Rediscovered.LeatherQuiver || itemstack.getItem() == mod_Rediscovered.ChainQuiver || itemstack.getItem() == mod_Rediscovered.GoldQuiver || itemstack.getItem() == mod_Rediscovered.IronQuiver || itemstack.getItem() == mod_Rediscovered.DiamondQuiver || itemstack.getItem() == mod_Rediscovered.LeatherChainQuiver))
		        {
		            if (player.inventory.hasItem(Items.arrow) || flag)
		            {
		                EntityArrow entityarrow = new EntityArrow(player.worldObj, player, 1.0F);
		                player.worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		                entityarrow.setIsCritical(true);
		
		                
		
		                if (!player.worldObj.isRemote)
		                {
		                	if (!flag)
			                {
			                	player.inventory.consumeInventoryItem(Items.arrow);
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
	@SubscribeEvent
    public void onArrowLooseEvent(ArrowLooseEvent event)
    {
		EntityPlayer player = event.entityPlayer;
		InventoryPlayer inv = player.inventory;
		ItemStack par1ItemStack = inv.getStackInSlot(inv.currentItem);
		ItemStack blah = new ItemStack(Items.bow);
		ItemStack quiver = new ItemStack(mod_Rediscovered.Quiver);
		ItemStack lquiver = new ItemStack(mod_Rediscovered.LeatherQuiver);
		ItemStack cquiver = new ItemStack(mod_Rediscovered.ChainQuiver);
		ItemStack gquiver = new ItemStack(mod_Rediscovered.GoldQuiver);
		ItemStack iquiver = new ItemStack(mod_Rediscovered.IronQuiver);
		ItemStack dquiver = new ItemStack(mod_Rediscovered.DiamondQuiver);
		ItemStack squiver = new ItemStack(mod_Rediscovered.LeatherChainQuiver);
		if(inv.getCurrentItem().equals(blah))
		{
			
		        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		        ItemStack itemstack = player.inventory.armorInventory[2];
		
		        if (itemstack != null && (itemstack.equals(quiver) || itemstack.equals(lquiver) || itemstack.equals(cquiver) || itemstack.equals(iquiver) || itemstack.equals(gquiver) || itemstack.equals(dquiver) || itemstack.equals(squiver)))
		        {
		        	event.setCanceled(true);
		        }
	        
		}
    }
    
	
	//Lantern
    @SubscribeEvent
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

                    if (p.worldObj.getBlock(pos.x, pos.y, pos.z).equals(Blocks.air))
                    {
                        p.worldObj.setBlock(pos.x, pos.y, pos.z, mod_Rediscovered.Lantern);
                    }

                    if (mod_Rediscovered.usernameLastPosMap.containsKey(p.getDisplayName()))
                    {
                        Pos3 pos2 = mod_Rediscovered.usernameLastPosMap.get(p.getDisplayName());

                        if ((pos2.x != pos.x || pos2.y != pos.y || pos2.z != pos.z) && p.worldObj.getBlock(pos2.x, pos2.y, pos2.z).equals(mod_Rediscovered.Lantern))
                        {
                            p.worldObj.setBlock(pos2.x, pos2.y, pos2.z, Blocks.air);
                        }
                    }

                    mod_Rediscovered.usernameLastPosMap.put(p.getDisplayName(), pos);
                }
                else
                {
                    if (mod_Rediscovered.usernameLastPosMap.containsKey(p.getDisplayName()))
                    {
                        Pos3 pos = mod_Rediscovered.usernameLastPosMap.get(p.getDisplayName());

                        if (p.worldObj.getBlock(pos.x, pos.y, pos.z).equals(mod_Rediscovered.Lantern))
                        {
                            p.worldObj.setBlock(pos.x, pos.y, pos.z, Blocks.air);
                        }

                        mod_Rediscovered.usernameLastPosMap.remove(p.getDisplayName());
                    }
                }
            }
        }
    }
    
    
    
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
	    if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		    ExtendedPlayer.register((EntityPlayer) event.entity);
    }
    
    public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates, boolean par2, EntityPlayerMP player)
    {
//    	player.addChatComponentMessage(new ChatComponentText("" + player.mcServer.worldServerForDimension(0).getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)));
        if (player.mcServer.worldServerForDimension(0).getBlock(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)== Blocks.bed)
        {
            return par1ChunkCoordinates;
        }
        else
        {
            return null;
        }
    }
}
