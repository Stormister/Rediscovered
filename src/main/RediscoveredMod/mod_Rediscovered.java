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

import java.util.HashMap;

import cpw.mods.fml.common.Mod.EventHandler;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStep;
import net.minecraft.block.BlockWood;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSign;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet23VehicleSpawn;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.lwjgl.input.Keyboard;

import Rediscovered.CommonProxyRediscovered;
import RediscoveredMod.BlockCherryLeaves.LeafCategory;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
 
@Mod(modid = mod_Rediscovered.modid, name = "Minecraft Rediscovered Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "RediscoveredMod" }, packetHandler = PacketHandler.class)

public class mod_Rediscovered
{
	public static final String modid = "rediscovered";
    @Instance(modid)
    public static mod_Rediscovered instance;
    public static HashMap<String, Pos3> usernameLastPosMap = new HashMap<String, Pos3>();
    public static final int guiIDLockedChest = 0;
    public static int rendererChairId;
    public static int rendererTableId;
    public static int rendererLecternId;
    public static int rendererLecternOpenId;
    public static int rendererEggId;
    public static int rendererLockedChestId;
    public static int rendererSpikesId;
    public static int rendererSpikesSideId;
	public Random ChunkGenRand;
    public int ChunkGenRandNum;
    public static BlockGearBase GearFloor;
    public static BlockGearBase GearWall;
    public static Item ItemGear;
    public static BlockHalfSlab DirtSlab;
    public static BlockHalfSlab DirtDoubleSlab;
    public static BlockHalfSlab CherrySlab;
    public static BlockHalfSlab CherryDoubleSlab;
    public static Block CherryStairs;
    public static Item Quiver;
    public static Item LeatherQuiver;
    public static Item ChainQuiver;
    public static Item GoldQuiver;
    public static Item IronQuiver;
    public static Item DiamondQuiver;
    public static Item LeatherChainHelmet;
    public static Item LeatherChainChest;
    public static Item LeatherChainLegs;
    public static Item LeatherChainBoots;
    public static Item Nausea;
    public static Item Blindness;
    public static Item Mining;
    public static Item JumpSplash;
    public static Block Sponge;
    public static Block TrickSponge;
    public static Block RubyOre;
    public static Block RubyBlock;
    public static Item  gemRuby;
    public static Block CryingObsidian;
    public static BlockCherryLog CherryLog;
    public static Block CherryPlank;
    public static BlockCherryLeaves CherryLeaves;
    public static Block CherrySapling;
    public static Item CherrySaplingItem;
    public static Block Rose;
    public static Item RoseItem;
    public static Block Spikes;
    public static Block SpikesSide;
    public static Item SpikesItem;
    public static Block DragonEggRed;
    public static Block LockedChest;
    public static BlockChair Chair;
    public static BlockTable Table;
    public static Block Lectern;
    public static Block LecternOpen;
    public static Item DreamBedItem;
    public static Block DreamBed;
    public static Block Lantern;
    public static Block LanternPhys;
    public static Item ItemLantern;
    public static Item Scarecrow;
    public static Item DreamPillow;
    
    
    public static int DimID;
    public static int HeavenBiomeID;
    public static boolean biomeSkyGen;
    public static boolean EnablePigmanVillages;
    public static boolean EnableSponge;
    public static boolean EnableSpongeGenerate;
    public static boolean EnableDungeonLoot;
    public static boolean EnableRubyOre;
    public static int CryingObsidianID;
    public static int FakeSpongeID;
    public static int TrickSpongeID;
    public static int GearFloorID;
    public static int GearFloorPoweredID;
    public static int GearWallID;
    public static int GearWallPoweredID;
    public static int DirtSlabID;
    public static int CherrySlabID;
    public static int DirtDoubleSlabID;
    public static int CherryDoubleSlabID;
    public static int RubyOreID;
    public static int RubyBlockID;
    public static int DragonEggID;
    public static int LockedChestID;
    public static int SpikesID;
    public static int SpikesSideID;
    public static int ChairID;
    public static int TableID;
    public static int LecternID;
    public static int LecternOpenID;
    public static int DreamBedID;
    public static int CherryWoodID;
    public static int CherryPlanksID;
    public static int CherryLeavesID;
    public static int CherrySaplingID;
    public static int RoseID;
    public static int CherryStairsID;
    public static int LanternID;
    public static int LanternPhysID;
    
    public static boolean QuickBow;
    public static boolean Calm4MusicDisk;
    public static int ItemGearID;
    public static int QuiverID;
    public static int LeatherQuiverID;
    public static int ChainQuiverID;
    public static int GoldQuiverID;
    public static int IronQuiverID;
    public static int DiamondQuiverID;
    public static int LCHelmetID;
    public static int LCChestID;
    public static int LCLegsID;
    public static int LCBootsID;
    public static int NauseaID;
    public static int BlindnessID;
    public static int BreathingID;
    public static int MiningID;
    public static int NauseaSplashID;
    public static int JumpSplashID;
    public static int BlindnessSplashID;
    public static int BreathingSplashID;
    public static int MiningSplashID;
    public static int RubyID;
    public static int SpikesItemID;
    public static int DreamBedItemID;
    public static int ItemLanternID;
    public static int ScarecrowItemID;
    public static int DreamPillowID;
    
    public static int DreamChance;
    public static int ZombieHorseSpawn;
    public static int SkeletonHorseSpawn;
    public static int RedDragonSpawn;
    public static int SkyChickenSpawn;
    public static int GiantSpawn;
    public static int FishSpawn;
    public static boolean anmen;
    public static boolean Pigman;
    public static boolean GVillager;
    public static boolean ZombieHorse;
    public static boolean SkeletonHorse;
    public static boolean RedDragon;
    public static boolean Giant;
    public static boolean SkyChicken;
    public static boolean Fish;
    public static boolean RanaSpawn;
    public static boolean SteveSpawn;
    public static boolean BlackSteveSpawn;
    public static boolean BeastBoySpawn;
    public static boolean GVillagerSpawn;
    public static boolean SteveHostile;
    public static boolean BlackSteveHostile;
    public static boolean BeastBoyHostile;
    public static boolean ScarecrowAttractsMobs;
    public static boolean DreamBedEnabled;
    public static boolean DreamPillowRecipe;
    public static boolean DaytimeBed;
    public static int ParrowID;
    public static int MountBlockID;
    public static int RanaID;
    public static int SteveID;
    public static int BlackSteveID;
    public static int BeastBoyID;
    public static int GiantID;
    public static int SkyChickenID;
    public static int FishID;
    public static int PigmanID;
    public static int GVillagerID;
    public static int ZombieHorseID;
    public static int SkeletonHorseID;
    public static int RedDragonID;
    public static int ScarecrowID;
    public static final String rana = new String(mod_Rediscovered.modid + ":" + "textures/models/rana.png");
    public static final String steve = new String(mod_Rediscovered.modid + ":" + "textures/models/Steve");
    public static final String blacksteve = new String(mod_Rediscovered.modid + ":" + "textures/models/BlackSteve");
    public static final String beastboy = new String(mod_Rediscovered.modid + ":" + "textures/models/BeastBoy");
    
    
    public static BiomeGenBase heaven;
    static EnumArmorMaterial EnumArmorMaterialInvinc = EnumHelper.addArmorMaterial("Invincible", -1, new int[] {0, 0, 0, 0}, 0);
    static EnumArmorMaterial EnumArmorMaterialLC = EnumHelper.addArmorMaterial("LC", 20, new int[] {3, 8, 6, 2}, 27);
    static EnumArmorMaterial EnumArmorMaterialCloth = EnumHelper.addArmorMaterial("Leather", 5, new int[]{1, 3, 2, 1}, 15);
    @SidedProxy(clientSide="Rediscovered.ClientProxyRediscovered", serverSide= "Rediscovered.CommonProxyRediscovered")
	public static CommonProxyRediscovered proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        Configuration c = new Configuration(e.getSuggestedConfigurationFile());
        c.load();

        //Blocks
        CryingObsidianID = c.getBlock("Crying Obsidian", 190).getInt();
        FakeSpongeID = c.getBlock("Fake Sponge (Not Vanilla)", 219).getInt();
        TrickSpongeID = c.getBlock("Trick Sponge (Also Not Vanilla)", 249).getInt();
        //Some random text goes here
        GearFloorID = c.getBlock("Gear Floor", 216).getInt();
        GearFloorPoweredID = c.getBlock("Gear Floor Powered", 217).getInt();
        GearWallPoweredID = c.getBlock("Gear Wall Powered", 248).getInt();
        GearWallID = c.getBlock("Gear Wall", 218).getInt();
        DirtSlabID = c.getBlock("Dirt Slab", 210).getInt();
        DirtDoubleSlabID = c.getBlock("Dirt Double Slab", 224).getInt();
        RubyOreID = c.getBlock("Ruby Ore", 196).getInt();
        RubyBlockID = c.getBlock("Ruby Block", 197).getInt();
        DragonEggID = c.getBlock("Red Dragon Egg", 213).getInt();
        LockedChestID = c.getBlock("Locked Chest", 208).getInt();
        SpikesID = c.getBlock("Spikes Floor", 220).getInt();
        SpikesSideID = c.getBlock("Spikes Wall", 221).getInt();
        ChairID = c.getBlock("Chair", 209).getInt();
        TableID = c.getBlock("Table", 214).getInt();
        LecternID = c.getBlock("Lectern Closed", 222).getInt();
        LecternOpenID = c.getBlock("Lectern Open", 223).getInt();
        DreamBedID = c.getBlock("DreamBed", 215).getInt();
        LanternID = c.getBlock("Lantern Light", 680).getInt();
        LanternPhysID = c.getBlock("Physical Lantern", 211).getInt();
        
        CherryPlanksID = c.getBlock("Cherry Planks", 241).getInt();
        CherryLeavesID = c.getBlock("Cherry Leaves", 242).getInt();
        CherrySaplingID = c.getBlock("Cherry Sapling", 243).getInt();
        CherryWoodID = c.getBlock("Cherry Log", 240).getInt();
        CherrySlabID = c.getBlock("Cherry Wood Slab", 244).getInt();
        CherryDoubleSlabID = c.getBlock("Cherry Double Wood Slab", 245).getInt();
        CherryStairsID = c.getBlock("Cherry Stairs", 246).getInt();
        RoseID = c.getBlock("Rose", 247).getInt();
        
        //Items
        ItemGearID = c.getItem("Gear Item", 3784).getInt();
        DiamondQuiverID = c.getItem("Diamond Quiver", 3782).getInt();
        IronQuiverID = c.getItem("Iron Quiver", 3781).getInt();
        GoldQuiverID = c.getItem("Gold Quiver", 3780).getInt();
        ChainQuiverID = c.getItem("Chain Quiver", 3779).getInt();
        LeatherQuiverID = c.getItem("Leather Quiver", 3778).getInt();
        QuiverID = c.getItem("Quiver", 3777).getInt();
        LCBootsID = c.getItem("Leather-Chain Boots", 3792).getInt();
        LCLegsID = c.getItem("Leather-Chain Leggings", 3791).getInt();
        LCChestID = c.getItem("Leather-Chain Chestplate", 3790).getInt();
        LCHelmetID = c.getItem("Leather-Chain Helmet", 3789).getInt();
        NauseaID = c.getItem("Nausea Potion", 3801).getInt();
        BlindnessID = c.getItem("Blindness Potion", 3804).getInt();
        BreathingID = c.getItem("Water Breathing Potion", 3802).getInt();
        MiningID = c.getItem("Mining Slowness Potion", 3803).getInt();
        NauseaSplashID = c.getItem("Nausea Splash Potion", 3806).getInt();
        JumpSplashID = c.getItem("Jump Splash Potion", 3810).getInt();
        BlindnessSplashID = c.getItem("Blindness Splash Potion", 3809).getInt();
        BreathingSplashID = c.getItem("Water Breathing Splash Potion", 3807).getInt();
        MiningSplashID = c.getItem("Mining Slowness Splash Potion", 3808).getInt();
        RubyID = c.getItem("Ruby", 3785).getInt();
        SpikesItemID = c.getItem("Spikes Item", 3787).getInt();
        DreamBedItemID = c.getItem("Dream Bed Item", 3786).getInt();
        ItemLanternID = c.getItem("Lantern Item", 10011).getInt();
        ScarecrowItemID = c.getItem("Scarecrow Item", 10012).getInt();
        DreamPillowID = c.getItem("DreamPillow", 10013).getInt();
        
        //Entity
        ParrowID = c.get("Entities", "Purple Arrow Entity ID", 88).getInt();
        MountBlockID = c.get("Entities", "Mountable Block Entity ID", 78).getInt();
        RanaID = c.get("Entities", "Rana Entity ID", 67).getInt();
        SteveID = c.get("Entities", "Steve Entity ID", 69).getInt();
        BlackSteveID = c.get("Entities", "Black Steve Entity ID", 79).getInt();
        BeastBoyID = c.get("Entities", "Beast Boy Entity ID", 80).getInt();
        GiantID = c.get("Entities", "Giant Entity ID", 81).getInt();
        SkyChickenID = c.get("Entities", "Sky Chicken ID", 68).getInt();
        FishID = c.get("Entities", "Fish Entity ID", 72).getInt();
        PigmanID = c.get("Entities", "Pigman Entity ID", 70).getInt();
        GVillagerID = c.get("Entities", "Green Villager Entity ID", 73).getInt();
        ZombieHorseID = c.get("Entities", "Zombie Horse Entity ID", 76).getInt();
        SkeletonHorseID = c.get("Entities", "Skeleton Horse Entity ID", 77).getInt();
        RedDragonID = c.get("Entities", "Red Dragon Entity ID", 74).getInt();
        ScarecrowID = c.get("Entities", "Scarecrow Entity ID", 75).getInt();
        
        //Dimension
        DimID = c.get("Dimension", "Sky Dimension ID", 23).getInt();
        HeavenBiomeID = c.get("Dimension", "Sky Biome ID", 26).getInt();
        
        
        
        //Booleans
        EnableSponge = c.get("Options", "Enable Sponges Suck up Water", true).getBoolean(true);
        EnableSpongeGenerate = c.get("Options", "Enable Sponges Appear in Ocean", true).getBoolean(true);
        EnableDungeonLoot = c.get("Options", "Enable Lanterns appear in Dungeon Chests", true).getBoolean(true);
        EnableRubyOre = c.get("Options", "Enable Ruby Ore Generates Underground", true).getBoolean(true);
        EnablePigmanVillages = c.get("Options", "Enable Pigman Villages in the Sky Dimension", true).getBoolean(true);
        QuickBow = c.get("Options", "Enable Quiver Affects Bow", true).getBoolean(true);
        RanaSpawn = c.get("Options", "Enable Rana Spawn in Villages", true).getBoolean(true);
        SteveSpawn = c.get("Options", "Enable Steve Spawn in Villages", true).getBoolean(true);
        SteveHostile = c.get("Options", "Enable Steve is Hostile", true).getBoolean(true);
        BlackSteveSpawn = c.get("Options", "Enable Black Steve Spawn in Pigmen Villages", true).getBoolean(true);
        BlackSteveHostile = c.get("Options", "Enable Black Steve is Hostile", true).getBoolean(true);
        BeastBoySpawn = c.get("Options", "Enable Beast Boy Spawn in Pigmen Villages", true).getBoolean(true);
        BeastBoyHostile = c.get("Options", "Enable Beast Boy is Hostile", true).getBoolean(true);
        anmen = c.get("Options", "Enable Steve, Black Steve, and Beast Boy jogging", true).getBoolean(true);
        GVillagerSpawn = c.get("Options", "Enable Green Villager Spawn in Villages", true).getBoolean(true);
        ZombieHorseSpawn = c.get("Options", "Zombie Horse Spawn Rate", 25).getInt();
        SkeletonHorseSpawn = c.get("Options", "Skeleton Horse Spawn Rate", 25).getInt();
        RedDragonSpawn = c.get("Options", "Red Dragon Spawn Rate", 25).getInt();
        SkyChickenSpawn = c.get("Options", "Sky Chicken Spawn Rate", 150).getInt();
        GiantSpawn = c.get("Options", "Giant Spawn Rate", 150).getInt();
        FishSpawn = c.get("Options", "Fish Spawn Rate", 150).getInt();
        ScarecrowAttractsMobs = c.get("Options", "Scarecrow attracts zombies (True by default. Set to false to have zombies avoid scarecrows)", true).getBoolean(true);
        DreamBedEnabled = c.get("Options", "Use old Dream Bed method of getting to Sky Dimension", false).getBoolean(false);
        DreamPillowRecipe = c.get("Options", "Enable Dream Pillow recipe (Only applies if Dream Bed method is disabled)", false).getBoolean(false);
        DreamChance = c.get("Options", "Percent chance out of 100 of going to Sky Dimension on sleep. Only applies if Dream Bed Method is disabled", 12).getInt();
        DaytimeBed = c.get("Options", "Can go to Sky Dimension without Restrictions (Daytime, Monsters nearby).", false).getBoolean(false);
        biomeSkyGen = c.get("Options", "Enable Heaven (Don't mess with this unless I explicitely state it)", true).getBoolean(false);
        c.save();
        heaven = (new BiomeGenSky(HeavenBiomeID)).setColor(16421912).setBiomeName("Heaven").setDisableRain();
        
    }
   
    @EventHandler
    public void load(FMLInitializationEvent fie)
    {
    	if(EnableSponge)
        {
    		Block.blocksList[Block.sponge.blockID] = null;
        	Sponge = (new BlockAbsorb(FakeSpongeID, "sponge")).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sponge").setCreativeTab(CreativeTabs.tabBlock);
        	
	    	GameRegistry.registerBlock(Sponge, "Fake Sponge");
	    	Block.blocksList[Block.sponge.blockID] = this.Sponge;
	        Item.itemsList[Sponge.blockID] = null;
	        
	        
	        TrickSponge = (new BlockFakeSponge(249, "sponge")).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sponge");
	        GameRegistry.registerBlock(TrickSponge, "Trick Sponge");
        }
        Quiver = (new ItemQuiver(QuiverID, EnumArmorMaterialInvinc, 0, 1, "Quiver")).setUnlocalizedName("Quiver").setCreativeTab(CreativeTabs.tabCombat);
        LeatherQuiver = (new ItemQuiver(LeatherQuiverID, EnumArmorMaterialCloth, 0, 1, "LQuiver")).setUnlocalizedName("LeatherQuiver").setContainerItem(Quiver).setCreativeTab(CreativeTabs.tabCombat);
        ChainQuiver = (new ItemQuiver(ChainQuiverID, EnumArmorMaterial.CHAIN, 0, 1, "CQuiver")).setUnlocalizedName("ChainQuiver").setContainerItem(Quiver).setCreativeTab(CreativeTabs.tabCombat);
        GoldQuiver = (new ItemQuiver(GoldQuiverID, EnumArmorMaterial.GOLD, 0, 1, "GQuiver")).setUnlocalizedName("GoldQuiver").setContainerItem(Quiver).setCreativeTab(CreativeTabs.tabCombat);
        IronQuiver = (new ItemQuiver(IronQuiverID, EnumArmorMaterial.IRON, 0, 1, "IQuiver")).setUnlocalizedName("IronQuiver").setContainerItem(Quiver).setCreativeTab(CreativeTabs.tabCombat);
        DiamondQuiver = (new ItemQuiver(DiamondQuiverID, EnumArmorMaterial.DIAMOND, 0, 1, "DQuiver")).setUnlocalizedName("DiamondQuiver").setContainerItem(Quiver).setCreativeTab(CreativeTabs.tabCombat);
        LeatherChainHelmet = (new ItemLC(LCHelmetID, EnumArmorMaterialLC, 0, 0, "LCHelmet")).setUnlocalizedName("LeatherChainHelmet").setCreativeTab(CreativeTabs.tabCombat);
        LeatherChainChest = (new ItemLC(LCChestID, EnumArmorMaterialLC, 0, 1, "LCChest")).setUnlocalizedName("LeatherChainChest").setCreativeTab(CreativeTabs.tabCombat);
        LeatherChainLegs = (new ItemLC(LCLegsID, EnumArmorMaterialLC, 0, 2, "LCLegs")).setUnlocalizedName("LeatherChainLegs").setCreativeTab(CreativeTabs.tabCombat);
        LeatherChainBoots = (new ItemLC(LCBootsID, EnumArmorMaterialLC, 0, 3, "LCBoots")).setUnlocalizedName("LeatherChainBoots").setCreativeTab(CreativeTabs.tabCombat);
        CryingObsidian = (new BlockCryingObsidian(CryingObsidianID, "cryingobsidian")).setHardness(50F).setResistance(2000F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("CryingObsidian").setCreativeTab(CreativeTabs.tabBlock);
        Spikes = (new BlockSpikes(SpikesID)).setHardness(1.5F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Spikes");
        SpikesSide = (new BlockSpikesSide(SpikesSideID)).setHardness(1.5F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("SpikesSide");
        SpikesItem = (new ItemSpikes(SpikesItemID, "SpikesItem")).setUnlocalizedName("ItemSpikes").setCreativeTab(CreativeTabs.tabDecorations);
        DragonEggRed = (new BlockDragonEggRed(DragonEggID, "dragonegg")).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.125F).setUnlocalizedName("DragonEggRed");
        RubyOre = (new BlockRubyOre(RubyOreID, "rubyore")).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("RubyOre").setCreativeTab(CreativeTabs.tabBlock);
        gemRuby = (new ItemRuby(RubyID, "Ruby")).setUnlocalizedName("gemRuby").setCreativeTab(CreativeTabs.tabMaterials);
        RubyBlock = (new BlockRuby(RubyBlockID, "rubyblock")).setHardness(5F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("RubyBlock").setCreativeTab(CreativeTabs.tabBlock);
        Nausea = (new ItemNauseaPotion(NauseaID, 0, 1.0F, false, "nausea")).setUnlocalizedName("nausea");
        Mining = (new ItemMiningPotion(MiningID, 0, 1.0F, false, "mining")).setUnlocalizedName("mining");
        Blindness = (new ItemBlindnessPotion(BlindnessID, 0, 1.0F, false, "blindness")).setUnlocalizedName("blindness");
        Lantern = new BlockLantern(LanternID).setUnlocalizedName("blockLantern");
        LanternPhys = new BlockLanternPhys(LanternPhysID, "lantern").setUnlocalizedName("blockLanternphys");
        ItemLantern = new ItemLantern(ItemLanternID, "Lantern").setUnlocalizedName("itemLanternOn");
        LockedChest = (new BlockLockedChest(LockedChestID, "lockedchest")).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("lockedchest").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
        Chair = (BlockChair)(new BlockChair(ChairID)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("chair");
        Table = (BlockTable)(new BlockTable(TableID, 0)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("table");
        Lectern = (new BlockLectern(LecternID)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("lectern");
        LecternOpen = (new BlockLecternOpen(LecternOpenID)).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("lectern");
        DirtSlab = (BlockHalfSlab)(new BlockDirtStep(DirtSlabID, false)).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setLightOpacity(1).setUnlocalizedName("DirtSlab").setCreativeTab(CreativeTabs.tabBlock);
        DirtDoubleSlab = (BlockHalfSlab)(new BlockDirtStep(DirtDoubleSlabID, true)).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("DirtSlab");
        CherrySlab = (BlockHalfSlab)(new BlockCherryStep(CherrySlabID, false)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setLightOpacity(1).setUnlocalizedName("CherrySlab").setCreativeTab(CreativeTabs.tabBlock);
        CherryDoubleSlab = (BlockHalfSlab)(new BlockCherryStep(CherryDoubleSlabID, true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("CherrySlab");
		Scarecrow = (new ItemScarecrow(ScarecrowItemID, "Scarecrow")).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("Scarecrow");
		if(!DreamBedEnabled)
			DreamPillow = (new ItemDreamPillow(DreamPillowID, "DreamPillow")).setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("DreamPillow");
		else{
			DreamBedItem = (new ItemDreamBed(DreamBedItemID, "DreamBedItem")).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("DreamBedItem");
        	DreamBed = (new BlockDreamBed(DreamBedID)).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("DreamBed");
		}
        Rose = (new BlockRose(RoseID, "rose")).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("rose");
        CherryLog = (BlockCherryLog)(new BlockCherryLog(CherryWoodID, "log_cherry")).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrylog");
        CherryLeaves = (BlockCherryLeaves)(new BlockCherryLeaves(CherryLeavesID, LeafCategory.CAT1)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("cherryleaves");
        CherryPlank = (new BlockCherryWood(CherryPlanksID, "planks_cherry")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cherrywood");
        CherrySapling = (new BlockCherrySapling(CherrySaplingID, "sapling_cherry")).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("cherrysapling");
        CherryStairs = (new BlockCherryStairs(CherryStairsID, CherryPlank, 0)).setUnlocalizedName("CherryStairs");
        ItemGear = (new ItemGear(ItemGearID, "Gear")).setUnlocalizedName("ItemGear").setCreativeTab(CreativeTabs.tabRedstone);
        GearFloor = (BlockGearBase)(new BlockGearFloor(GearFloorID)).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("signblah");
        GearWall = (BlockGearBase)(new BlockGearWall(GearWallID)).setHardness(1.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("signblah");
        
        EntityRegistry.registerModEntity(EntityParrow.class, "Parrow", ParrowID, this, 64, 20, false);
        EntityRegistry.registerModEntity(EntityMountableBlock.class, "EntityMountableBlock", MountBlockID, this, 250, 5, false);
        EntityRegistry.registerGlobalEntityID(EntityRana.class, "Rana", RanaID, 0x4c7129, 0xf0a5a2);
        EntityRegistry.registerGlobalEntityID(EntitySteve.class, "Steve", SteveID, 44975, 0xf6b201);
        EntityRegistry.registerGlobalEntityID(EntityBlackSteve.class, "BlackSteve", BlackSteveID, 10489616, 894731);
        EntityRegistry.registerGlobalEntityID(EntityBeastBoy.class, "BeastBoy", BeastBoyID, 0x9932cc, 5349438);
        EntityRegistry.registerGlobalEntityID(EntityPigman.class, "Pigman", PigmanID, 0xf0a5a2, 0xa1a1a1);
        EntityRegistry.registerGlobalEntityID(EntityMeleePigman.class, "MeleePigman", 83, 0xf0a5a2, 0xa1a1a1);
        EntityRegistry.registerGlobalEntityID(EntityRangedPigman.class, "RangedPigman", 84, 0xf0a5a2, 0xa1a1a1);
        EntityRegistry.registerGlobalEntityID(EntityGreenVillager.class, "GreenVillager", GVillagerID, 5651507, 7969893);
        EntityRegistry.registerGlobalEntityID(EntitySkyChicken.class, "SkyChicken", SkyChickenID);
        EntityRegistry.registerGlobalEntityID(EntityGiant.class, "Giant", GiantID, 2243405, 7969893);
        EntityRegistry.registerGlobalEntityID(EntityFish.class, "Fish", FishID, 44975, 2243405);
        EntityRegistry.registerGlobalEntityID(EntityZombieHorse.class, "ZombieHorse", ZombieHorseID, 0x4c7129, 15656192);
        EntityRegistry.registerGlobalEntityID(EntitySkeletonHorse.class, "SkeletonHorse", SkeletonHorseID, 12698049, 15656192);
        EntityRegistry.registerGlobalEntityID(EntityScarecrow.class, "Scarecrow", ScarecrowID);
        EntityRegistry.registerGlobalEntityID(EntityGoodDragon.class, "RedDragon", RedDragonID);
        
        new RediscoveredGuiHandler();
        MinecraftForge.EVENT_BUS.register(new RediscoveredEventHandler());
        proxy.registerRenderThings();
        
        EntityRegistry.addSpawn(EntityFish.class, FishSpawn, 5, 20, EnumCreatureType.waterCreature, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
        EntityRegistry.addSpawn(EntityZombieHorse.class, ZombieHorseSpawn, 1, 1, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.desert);
        EntityRegistry.addSpawn(EntitySkeletonHorse.class, SkeletonHorseSpawn, 1, 1, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.desert);
        EntityRegistry.addSpawn(EntityGiant.class, GiantSpawn, 2, 2, EnumCreatureType.creature, this.heaven);
        EntityRegistry.addSpawn(EntitySkyChicken.class, SkyChickenSpawn, 6, 6, EnumCreatureType.creature, this.heaven);
        	
        LanguageRegistry.instance().addStringLocalization("entity.RedDragon.name", "Red Dragon");
        LanguageRegistry.instance().addStringLocalization("entity.RedDragon.name", "en_PT", "Red dragon");
        LanguageRegistry.instance().addStringLocalization("entity.Rana.name", "Rana");
        LanguageRegistry.instance().addStringLocalization("entity.Rana.name", "en_PT", "Ye Cheerful Shipmate");
        LanguageRegistry.instance().addStringLocalization("entity.Steve.name", "Steve");
        LanguageRegistry.instance().addStringLocalization("entity.Steve.name", "en_PT", "Ye Long-Lost Hero");
        LanguageRegistry.instance().addStringLocalization("entity.BlackSteve.name", "Black Steve");
        LanguageRegistry.instance().addStringLocalization("entity.BlackSteve.name", "en_PT", "Ye Long-Lost Villain");
        LanguageRegistry.instance().addStringLocalization("entity.BeastBoy.name", "Beast Boy");
        LanguageRegistry.instance().addStringLocalization("entity.BeastBoy.name", "en_PT", "Ye Teenage Titan");
        LanguageRegistry.instance().addStringLocalization("entity.SkyChicken.name", "Sky Chicken");
        LanguageRegistry.instance().addStringLocalization("entity.SkyChicken.name", "en_PT", "Ridable Fowl");
        LanguageRegistry.instance().addStringLocalization("entity.Pigman.name", "Pigman");
        LanguageRegistry.instance().addStringLocalization("entity.Pigman.name", "en_PT", "Ye Thief o' the Desert");
        LanguageRegistry.instance().addStringLocalization("entity.MeleePigman.name", "Melee Pigman");
        LanguageRegistry.instance().addStringLocalization("entity.MeleePigman.name", "en_PT", "Ye Soldier o' the Desert");
        LanguageRegistry.instance().addStringLocalization("entity.RangedPigman.name", "Ranged Pigman");
        LanguageRegistry.instance().addStringLocalization("entity.RangedPigman.name", "en_PT", "Ye Archer o' the Desert");
        LanguageRegistry.instance().addStringLocalization("entity.GreenVillager.name", "Villager");
        LanguageRegistry.instance().addStringLocalization("entity.GreenVillager.name", "en_PT", "Green Landlubber");
        LanguageRegistry.instance().addStringLocalization("entity.Fish.name", "Fish");
        LanguageRegistry.instance().addStringLocalization("entity.Fish.name", "en_PT", "Ye Catch o' the Day");
        LanguageRegistry.instance().addStringLocalization("entity.ZombieHorse.name", "Zombie Horse");
        LanguageRegistry.instance().addStringLocalization("entity.ZombieHorse.name", "en_PT", "Undead Horse");
        LanguageRegistry.instance().addStringLocalization("entity.SkeletonHorse.name", "Skeleton Horse");
        LanguageRegistry.instance().addStringLocalization("entity.SkeletonHorse.name", "en_PT", "Bony Horse");
        LanguageRegistry.instance().addStringLocalization("death.attack.parrow", "%1$s was shot by skeleton.");
        LanguageRegistry.instance().addStringLocalization("tile.DirtSlab.dirt.name", "Dirt Slab");
        LanguageRegistry.instance().addStringLocalization("tile.CherrySlab.wood.name", "Cherry Wood Slab");
        LanguageRegistry.instance().addStringLocalization("entity.Scarecrow.name", "Scarecrow");
        LanguageRegistry.addName(Quiver, "Quiver");        
        GameRegistry.addRecipe(new ItemStack(Quiver, 1), new Object[]
                {
                    "FLL", "SLL", "SLL", Character.valueOf('L'), Item.leather, Character.valueOf('F'), Item.feather, Character.valueOf('S'), Item.silk
                });
        LanguageRegistry.addName(LeatherQuiver, "Leather Tunic with Quiver");        
        GameRegistry.addShapelessRecipe(new ItemStack(LeatherQuiver, 1), new Object[]
                {
                     Quiver, Item.plateLeather
                });
        GameRegistry.addRecipe(new ItemStack(Item.plateLeather, 1), new Object[]
                {
                    "L", Character.valueOf('L'), LeatherQuiver
                });
        LanguageRegistry.addName(ChainQuiver, "Chain Chestplate with Quiver");        
        GameRegistry.addShapelessRecipe(new ItemStack(ChainQuiver, 1), new Object[]
                {
                    Quiver, Item.plateChain
                });
        GameRegistry.addRecipe(new ItemStack(Item.plateChain, 1), new Object[]
                {
                    "L", Character.valueOf('L'), ChainQuiver
                });
        LanguageRegistry.addName(GoldQuiver, "Gold Chestplate with Quiver");        
        GameRegistry.addShapelessRecipe(new ItemStack(GoldQuiver, 1), new Object[]
                {
                    Quiver, Item.plateGold
                });
        GameRegistry.addRecipe(new ItemStack(Item.plateGold, 1), new Object[]
                {
                    "L", 'L', GoldQuiver
                });
        LanguageRegistry.addName(IronQuiver, "Iron Chestplate with Quiver");        
        GameRegistry.addShapelessRecipe(new ItemStack(IronQuiver, 1), new Object[]
                {
                    Quiver, Item.plateIron
                });
        GameRegistry.addRecipe(new ItemStack(Item.plateIron, 1), new Object[]
                {
                    "L", Character.valueOf('L'), IronQuiver
                });
        LanguageRegistry.addName(DiamondQuiver, "Diamond Chestplate with Quiver");
        GameRegistry.addShapelessRecipe(new ItemStack(DiamondQuiver, 1), new Object[]
                {
                    Quiver, Item.plateDiamond
                });
        GameRegistry.addRecipe(new ItemStack(Item.plateDiamond, 1), new Object[]
                {
                    "L", Character.valueOf('L'), DiamondQuiver
                });
        LanguageRegistry.addName(LeatherChainHelmet, "Leather-Chain Helmet");
        GameRegistry.addRecipe(new ItemStack(LeatherChainHelmet, 1), new Object[]
                {
                    "L", "C", Character.valueOf('L'), Item.helmetLeather, Character.valueOf('C'), Item.helmetChain
                });
        LanguageRegistry.addName(LeatherChainChest, "Leather-Chain Chestplate");
        GameRegistry.addRecipe(new ItemStack(LeatherChainChest, 1), new Object[]
                {
                    "L", "C", Character.valueOf('L'), Item.plateLeather, Character.valueOf('C'), Item.plateChain
                });
        LanguageRegistry.addName(LeatherChainLegs, "Leather-Chain Leggings");
        GameRegistry.addRecipe(new ItemStack(LeatherChainLegs, 1), new Object[]
                {
                    "L", "C", Character.valueOf('L'), Item.legsLeather, Character.valueOf('C'), Item.legsChain
                });
        LanguageRegistry.addName(LeatherChainBoots, "Leather-Chain Boots");
        GameRegistry.addRecipe(new ItemStack(LeatherChainBoots, 1), new Object[]
                {
                    "L", "C", Character.valueOf('L'), Item.bootsLeather, Character.valueOf('C'), Item.bootsChain
                });
        LanguageRegistry.addName(Rose, "Rose");
        GameRegistry.registerBlock(Rose, "Rose");
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 1), new Object[]
                {
                    Rose
                });
        LanguageRegistry.addName(CherryLog, "Cherry Log");
        GameRegistry.registerBlock(CherryLog, "CherryLog");
        OreDictionary.registerOre("woodLog", CherryLog);
        OreDictionary.registerOre("logWood", CherryLog);
        LanguageRegistry.addName(CherryPlank, "Cherry Planks");
        GameRegistry.registerBlock(CherryPlank, "CherryPlank");
        OreDictionary.registerOre("plankWood", CherryPlank);
        GameRegistry.addShapelessRecipe(new ItemStack(CherryPlank, 4), new Object[]
                {
                    CherryLog
                });
        LanguageRegistry.addName(CherrySlab, "Cherry Slab");
        GameRegistry.registerBlock(CherrySlab, "CherrySlab");
        OreDictionary.registerOre("woodSlab", CherrySlab);
        OreDictionary.registerOre("slabWood", CherrySlab);
        GameRegistry.addRecipe(new ItemStack(CherrySlab, 6), new Object[]
                {
                    "SSS", Character.valueOf('S'), CherryPlank
                });
        GameRegistry.registerBlock(CherryDoubleSlab, "CherryDoubleSlab");
        OreDictionary.registerOre("woodSlab", CherryDoubleSlab);
        OreDictionary.registerOre("slabWood", CherryDoubleSlab);
        LanguageRegistry.addName(CherryLeaves, "Cherry Leaves");
        GameRegistry.registerBlock(CherryLeaves, "CherryLeaves");
        OreDictionary.registerOre("leavesTree", CherryLeaves);
        LanguageRegistry.addName(CherrySapling, "Cherry Sapling");
        GameRegistry.registerBlock(CherrySapling, "CherrySapling");
        OreDictionary.registerOre("saplingTree", CherrySapling);
        LanguageRegistry.addName(CherryStairs, "Cherry Stairs");
        GameRegistry.registerBlock(CherryStairs, "CherryStairs");
        OreDictionary.registerOre("stairWood", CherryStairs);
        OreDictionary.registerOre("woodStair", CherryStairs);
        GameRegistry.addRecipe(new ItemStack(CherryStairs, 4), new Object[]
                {
                    "  S", " SS", "SSS", Character.valueOf('S'), CherryPlank
                });
        LanguageRegistry.addName(CryingObsidian, "Crying Obsidian");
        GameRegistry.registerBlock(CryingObsidian, "CryingObsidian");
        MinecraftForge.setBlockHarvestLevel(CryingObsidian, "pickaxe", 3);
        GameRegistry.addRecipe(new ItemStack(CryingObsidian, 1), new Object[]
                {
                    " L ", "LOL", " L ", Character.valueOf('L'), new ItemStack(Item.dyePowder, 1, 4), Character.valueOf('O'), Block.obsidian
                });
        LanguageRegistry.addName(SpikesItem, "Spikes");
        GameRegistry.registerBlock(Spikes, "SpikesFloor");
        GameRegistry.registerBlock(SpikesSide, "SpikesSide");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SpikesItem, 1), true, new Object[]
                {
                    "   ", "I I", "LLL", Character.valueOf('L'), "plankWood", Character.valueOf('I'), Item.ingotIron
                }));
        LanguageRegistry.addName(Scarecrow, "Scarecrow");   
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Scarecrow, 1), true, new Object[]
                {
                    " P ", "SHS", " S ", 'S', Item.stick, 'H', Block.hay, 'P', Block.pumpkin
                }));
        if(DreamBedEnabled){
        	LanguageRegistry.addName(DreamBedItem, "Dream Bed"); 
        	GameRegistry.registerBlock(DreamBed, "DreamBed");
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DreamBedItem, 1), true, new Object[]
                    {
                        "   ", "WWW", "OGO", 'G', Block.glowStone, 'W', new ItemStack(Block.cloth, 1, 11), 'O', "plankWood"
                    }));
        }
        else{
        	LanguageRegistry.addName(DreamPillow, "Dream Pillow");
        	if(DreamPillowRecipe){
        		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DreamPillow, 1), true, new Object[]
                        {
                            "WWW", "WGW", "WWW", 'G', Item.glowstone, 'W', new ItemStack(Block.cloth, 1, 11)
                        }));
        	}
        }
        LanguageRegistry.addName(DragonEggRed, "Red Dragon Egg");
        GameRegistry.registerBlock(DragonEggRed, "DragonEggRed");
        MinecraftForge.setBlockHarvestLevel(DragonEggRed, "pickaxe", 2);
        LanguageRegistry.addName(RubyOre, "Ruby Ore");
        GameRegistry.registerBlock(RubyOre, "RubyOre");
        OreDictionary.registerOre("oreRuby", RubyOre);
        LanguageRegistry.addName(RubyBlock, "Ruby Block");
        GameRegistry.registerBlock(RubyBlock, "RubyBlock");
        MinecraftForge.setBlockHarvestLevel(RubyBlock, "pickaxe", 1);
        LanguageRegistry.addName(gemRuby, "Ruby");
        OreDictionary.registerOre("gemRuby", gemRuby);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RubyBlock, 1), true, new Object[]
                {
                    "LLL", "LLL", "LLL", Character.valueOf('L'), "gemRuby"
                }));
        GameRegistry.addSmelting(RubyOre.blockID, new ItemStack (gemRuby), 1);
        MinecraftForge.setBlockHarvestLevel(RubyOre, "pickaxe", 2);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(gemRuby, 9), true, new Object[]
                {
                    "L", 'L', RubyBlock
                }));
        LanguageRegistry.addName(Nausea, "Potion of Nausea");
        GameRegistry.addShapelessRecipe(new ItemStack(Nausea, 1), new Object[]
                {
                    Item.rottenFlesh, new ItemStack(Item.potion, 1, 16)
                });
        LanguageRegistry.addName(Blindness, "Potion of Blindness");
        GameRegistry.addShapelessRecipe(new ItemStack(Blindness, 1), new Object[]
                {
                    Item.poisonousPotato, new ItemStack(Item.potion, 1, 16)
                });
        LanguageRegistry.addName(Mining, "Potion of Slow Mining");
        GameRegistry.addShapelessRecipe(new ItemStack(Mining, 1), new Object[]
                {
                    Item.appleRed, new ItemStack(Item.potion, 1, 16)
                });
        LanguageRegistry.addName(DirtSlab, "Dirt Slab");
        GameRegistry.registerBlock(DirtSlab, "DirtSlab");
        GameRegistry.registerBlock(DirtDoubleSlab, "DirtDoubleSlab");
        GameRegistry.addRecipe(new ItemStack(DirtSlab, 6), new Object[]
                {
                    "DDD", Character.valueOf('D'), Block.dirt
                });
        LanguageRegistry.addName(LockedChest, "Locked Chest");
        GameRegistry.registerBlock(LockedChest, "lockedchest");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(LockedChest, 1), new Object[]
                {
                    "WWW", "WIW", "WWW", 'I', Item.ingotIron, 'W', "plankWood"
                }));
        LanguageRegistry.addName(Chair, "Chair");
        GameRegistry.registerBlock(Chair, "Chair");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chair, 1), true, new Object[]
                {
                    "L  ", "LLL", "L L", Character.valueOf('L'), "plankWood"
                }));
        LanguageRegistry.addName(Table, "Table");
        GameRegistry.registerBlock(Table, "Table");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Table, 1), true, new Object[]
                {
                    "   ", "LLL", "L L", Character.valueOf('L'), "plankWood"
                }));
        LanguageRegistry.addName(Lectern, "Lectern");
        GameRegistry.registerBlock(Lectern, "Lectern");
        GameRegistry.registerBlock(LecternOpen, "LecternOpen");
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Lectern, 1), true, new Object[]
                {
                    " B ", " L ", "LGL", Character.valueOf('L'), "plankWood", Character.valueOf('G'), Item.ingotGold, Character.valueOf('B'), Item.book
                }));
        LanguageRegistry.addName(ItemGear, "Gear");
        GameRegistry.registerBlock(GearFloor, "GearFloor");
        GameRegistry.registerBlock(GearWall, "GearWall");
        MinecraftForge.setBlockHarvestLevel(GearFloor, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(GearWall, "pickaxe", 0);
        GameRegistry.addRecipe(new ItemStack(ItemGear, 8), new Object[]
                {
                    " I ", "III", " I ", Character.valueOf('I'), Item.ingotIron
                });
        LanguageRegistry.addName(ItemLantern, "Lantern");
        GameRegistry.registerBlock(Lantern, "Lantern");
        GameRegistry.addRecipe(new ItemStack(ItemLantern, 1), new Object[]
                {
                    " I ", " S ", " I ", Character.valueOf('I'), Item.ingotIron, Character.valueOf('S'), Item.glowstone
                });
        DimensionManager.registerProviderType(DimID, WorldProviderHeaven.class, true);
        DimensionManager.registerDimension(DimID, DimID);
        
        if(EnableRubyOre)
        {
        	GameRegistry.registerWorldGenerator(new WorldGeneratorRuby());
        }
        if(EnableSpongeGenerate)
        {
        	GameRegistry.registerWorldGenerator(new WorldGeneratorSponge());
        }
        if(EnablePigmanVillages)
        {
        	GameRegistry.registerWorldGenerator(new WorldGeneratorPigmanVillage());
        }
        if(EnableDungeonLoot)
        {
        	ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST ).addItem(new WeightedRandomChestContent(new ItemStack(ItemLantern), 1, 1, 15));
        	ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(ItemLantern), 1, 1, 15));
        }
    	
    	
    	GameRegistry.registerTileEntity(TileEntityChair.class, "tileentitychair");
    	GameRegistry.registerTileEntity(TileEntityTable.class, "tileentitytable");
    	GameRegistry.registerTileEntity(TileEntityLectern.class, "tileentitylectern");
    	GameRegistry.registerTileEntity(TileEntityLecternOpen.class, "tileentitylecternopen");
    	GameRegistry.registerTileEntity(TileEntityRedEgg.class, "tileentityegg");
    	GameRegistry.registerTileEntity(TileEntityLockedChest.class, "tileentitylockedchest");
    	GameRegistry.registerTileEntity(TileEntitySpikes.class, "tileentityspikes");
    	GameRegistry.registerTileEntity(TileEntitySpikesSide.class, "tileentityspikesside");
    	TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
    	
    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
    	Item.itemsList[DirtSlab.blockID] = (new ItemSlab(DirtSlab.blockID - 256, (BlockHalfSlab)DirtSlab, (BlockHalfSlab)DirtDoubleSlab, false));
    	Item.itemsList[CherrySlab.blockID] = (new ItemSlab(CherrySlab.blockID - 256, (BlockHalfSlab)CherrySlab, (BlockHalfSlab)CherryDoubleSlab, false));
    }
    
    public static boolean hasLitLanternOnHotbar(InventoryPlayer inv)
    {
        for (int i = 0; i < 9; i++)
        {
            ItemStack item = inv.mainInventory[i];

            if (item != null && item.itemID == mod_Rediscovered.ItemLantern.itemID)
            {
                return true;
            }
        }

        return false;
    }
    
    public static DamageSource causeParrowDamage(EntityParrow par0EntityDarkArrow, Entity par1Entity)
    {
    	return (new EntityDamageSourceIndirect("parrow", par0EntityDarkArrow, par1Entity)).setProjectile();
    }   

    public String getVersion()
    {
        return "1.6.4";
    }

	
	public String getModName() 
	{
		return "rediscovered";
	}
 
}
