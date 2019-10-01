package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;



public class ClientProxyRediscovered extends CommonProxyRediscovered
{
	private static int renderIdRedEgg;
	private static int rendererChairId;
	private static int rendererTableId;
	private static int rendererLecternId;
	private static int rendererLecternOpenId;
	private static int rendererLockedChestId;
	
	@Override
	public void registerRenderThings()
	{
		ClientProxyRediscovered.renderIdRedEgg = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedEgg.class, new RenderTileEntityRedEgg());
		RenderingRegistry.registerBlockHandler(new RenderBlockRedEgg(ClientProxyRediscovered.renderIdRedEgg));
		
		ClientProxyRediscovered.rendererChairId = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new RenderTileEntityChair());
		RenderingRegistry.registerBlockHandler(new RenderBlockChair(ClientProxyRediscovered.rendererChairId));
		
		ClientProxyRediscovered.rendererTableId = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTileEntityTable());
		RenderingRegistry.registerBlockHandler(new RenderBlockTable(ClientProxyRediscovered.rendererTableId));
		
		ClientProxyRediscovered.rendererLecternId = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLectern.class, new RenderTileEntityLectern());
		RenderingRegistry.registerBlockHandler(new RenderBlockLectern(ClientProxyRediscovered.rendererLecternId));
		
		ClientProxyRediscovered.rendererLecternOpenId = RenderingRegistry.getNextAvailableRenderId();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLecternOpen.class, new RenderTileEntityLecternOpen());
		RenderingRegistry.registerBlockHandler(new RenderBlockLecternOpen(ClientProxyRediscovered.rendererLecternOpenId));
		
//		ClientProxyRediscovered.rendererLockedChestId = RenderingRegistry.getNextAvailableRenderId();
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLockedChest.class, new RenderTileEntityLockedChest());
//		RenderingRegistry.registerBlockHandler(new RenderBlockLockedChest(ClientProxyRediscovered.rendererLockedChestId));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpikes.class, new RenderTileEntitySpikes());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpikesSide.class, new RenderTileEntitySpikesSide());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_Rediscovered.Spikes), new RenderItemSpikes());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_Rediscovered.SpikesSide), new RenderItemSpikesSide());
		
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityRana.class, new RenderMD3(false, "/mcexport01.MD3", mod_Rediscovered.rana));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntitySteve.class, new EntitySteve.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.steve ));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityBlackSteve.class, new EntityBlackSteve.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.blacksteve ));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityBeastBoy.class, new EntityBeastBoy.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.beastboy ));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityPigman.class, new RenderPigman(new ModelBiped(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityMeleePigman.class, new RenderPigman(new ModelBiped(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityRangedPigman.class, new RenderPigman(new ModelRangedPigman(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityGreenVillager.class, new RenderGreenVillager());
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntitySkyChicken.class, new RenderSkyChicken(new ModelSkyChicken(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityFish.class, new RenderFishMob(new ModelFish(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityZombieHorse.class, new RenderZombieHorse(new ModelZombieHorse(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntitySkeletonHorse.class, new RenderSkeletonHorse(new ModelSkeletonHorse(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityGoodDragon.class, new RenderRedDragon());
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityParrow.class, new RenderParrow());
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityGiant.class, new RenderGiant(new ModelZombie(), 0.5F, 6.0F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityScarecrow.class, new RenderScarecrow(new ModelScarecrow(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(com.stormister.rediscovered.EntityRediscoveredPotion.class, new RenderRediscoveredPotion());
    	
//		MinecraftForge.EVENT_BUS.register(new Ambiance());
	}
	
	@Override
	public int getBlockRender(Block blockID)
	{
		
		if (blockID == mod_Rediscovered.DragonEggRed)
		{
			return ClientProxyRediscovered.renderIdRedEgg;
		}
		else if (blockID == mod_Rediscovered.Chair)
		{
			return ClientProxyRediscovered.rendererChairId;
		}
		else if (blockID == mod_Rediscovered.Table)
		{
			return ClientProxyRediscovered.rendererTableId;
		}
		else if (blockID == mod_Rediscovered.Lectern)
		{
			return ClientProxyRediscovered.rendererLecternId;
		}
		else if (blockID == mod_Rediscovered.LecternOpen)
		{
			return ClientProxyRediscovered.rendererLecternOpenId;
		}
		else if (blockID == mod_Rediscovered.LockedChest)
		{
			return ClientProxyRediscovered.rendererLockedChestId;
		}
		

		return -1;
	}
	
	public void registerTileEntitySpecialRenderer(){
		
	}
	
//	@Override
//    public World getClientWorld()
//    {
//        return FMLClientHandler.instance().getClient().theWorld;
//    }

    

}
