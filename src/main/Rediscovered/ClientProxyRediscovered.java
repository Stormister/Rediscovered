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












package Rediscovered;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import Rediscovered.CommonProxyRediscovered;
import RediscoveredMod.EntityBeastBoy;
import RediscoveredMod.EntityBlackSteve;
import RediscoveredMod.EntitySteve;
import RediscoveredMod.ModelFish;
import RediscoveredMod.ModelRangedPigman;
import RediscoveredMod.ModelScarecrow;
import RediscoveredMod.ModelSkeletonHorse;
import RediscoveredMod.ModelSkyChicken;
import RediscoveredMod.ModelTestDragon;
import RediscoveredMod.ModelZombieHorse;
import RediscoveredMod.RenderChair;
import RediscoveredMod.RenderFishMob;
import RediscoveredMod.RenderGiant;
import RediscoveredMod.RenderGreenVillager;
import RediscoveredMod.RenderItemChair;
import RediscoveredMod.RenderItemLectern;
import RediscoveredMod.RenderItemLecternOpen;
import RediscoveredMod.RenderItemRedEgg;
import RediscoveredMod.RenderItemSpikes;
import RediscoveredMod.RenderItemSpikesSide;
import RediscoveredMod.RenderItemTable;
import RediscoveredMod.RenderLectern;
import RediscoveredMod.RenderLecternOpen;
import RediscoveredMod.RenderMD3;
import RediscoveredMod.RenderParrow;
import RediscoveredMod.RenderPigman;
import RediscoveredMod.RenderRedDragon;
import RediscoveredMod.RenderRedEgg;
import RediscoveredMod.RenderScarecrow;
import RediscoveredMod.RenderSkeletonHorse;
import RediscoveredMod.RenderSkyChicken;
import RediscoveredMod.RenderSpikes;
import RediscoveredMod.RenderSpikesSide;
import RediscoveredMod.RenderTestDragon;
import RediscoveredMod.RenderZombieHorse;
import RediscoveredMod.TileEntityChair;
import RediscoveredMod.TileEntityLectern;
import RediscoveredMod.TileEntityLecternOpen;
import RediscoveredMod.TileEntityLockedChest;
import RediscoveredMod.TileEntityRedEgg;
import RediscoveredMod.TileEntitySpikes;
import RediscoveredMod.TileEntitySpikesSide;
import RediscoveredMod.TileEntityTable;
import RediscoveredMod.TileEntityTableRenderer;
import RediscoveredMod.mod_Rediscovered;

public class ClientProxyRediscovered extends CommonProxyRediscovered
{
	
	@Override
	public void registerRenderThings()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new RenderChair());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TileEntityTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLectern.class, new RenderLectern());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLecternOpen.class, new RenderLecternOpen());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedEgg.class, new RenderRedEgg());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpikes.class, new RenderSpikes());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpikesSide.class, new RenderSpikesSide());
		mod_Rediscovered.rendererChairId = RenderingRegistry.getNextAvailableRenderId();
		mod_Rediscovered.rendererEggId = RenderingRegistry.getNextAvailableRenderId();
		mod_Rediscovered.rendererTableId = RenderingRegistry.getNextAvailableRenderId();
		mod_Rediscovered.rendererSpikesId = RenderingRegistry.getNextAvailableRenderId();
		mod_Rediscovered.rendererSpikesSideId = RenderingRegistry.getNextAvailableRenderId();
		mod_Rediscovered.rendererLecternId = RenderingRegistry.getNextAvailableRenderId();
		mod_Rediscovered.rendererLecternOpenId = RenderingRegistry.getNextAvailableRenderId();
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.Chair.blockID, new RenderItemChair());
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.Table.blockID, new RenderItemTable());
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.Lectern.blockID, new RenderItemLectern());
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.LecternOpen.blockID, new RenderItemLecternOpen());
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.DragonEggRed.blockID, new RenderItemRedEgg());
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.Spikes.blockID, new RenderItemSpikes());
		MinecraftForgeClient.registerItemRenderer(mod_Rediscovered.SpikesSide.blockID, new RenderItemSpikesSide());
		
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityRana.class, new RenderMD3(false, "/mcexport01.MD3", mod_Rediscovered.rana));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntitySteve.class, new EntitySteve.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.steve ));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityBlackSteve.class, new EntityBlackSteve.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.blacksteve ));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityBeastBoy.class, new EntityBeastBoy.RenderMD3Steve(mod_Rediscovered.anmen, "/Steve.MD3", mod_Rediscovered.beastboy ));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityPigman.class, new RenderPigman(new ModelBiped(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityMeleePigman.class, new RenderPigman(new ModelBiped(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityRangedPigman.class, new RenderPigman(new ModelRangedPigman(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityGreenVillager.class, new RenderGreenVillager());
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntitySkyChicken.class, new RenderSkyChicken(new ModelSkyChicken(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityFish.class, new RenderFishMob(new ModelFish(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityZombieHorse.class, new RenderZombieHorse(new ModelZombieHorse(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntitySkeletonHorse.class, new RenderSkeletonHorse(new ModelSkeletonHorse(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityGoodDragon.class, new RenderRedDragon());
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityParrow.class, new RenderParrow());
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityTestDragon.class, new RenderTestDragon(new ModelTestDragon(), 0.5F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityGiant.class, new RenderGiant(new ModelZombie(), 0.5F, 6.0F));
    	RenderingRegistry.instance().registerEntityRenderingHandler(RediscoveredMod.EntityScarecrow.class, new RenderScarecrow(new ModelScarecrow(), 0.5F));
	}
	
//	@Override
//    public World getClientWorld()
//    {
//        return FMLClientHandler.instance().getClient().theWorld;
//    }

    

}
