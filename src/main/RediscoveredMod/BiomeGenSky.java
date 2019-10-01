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

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSky extends BiomeGenBase
{
    public BiomeGenSky(int par1)
    {
        super(par1);
        this.minHeight = 3.0F;
        this.maxHeight = 4.0F;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityGiant.class, mod_Rediscovered.GiantSpawn, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityGoodDragon.class, mod_Rediscovered.RedDragonSpawn, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySkyChicken.class, mod_Rediscovered.SkyChickenSpawn, 6, 6));
        this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityFish.class, mod_Rediscovered.FishSpawn, 5, 20));
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenTrees(true) : (par1Random.nextInt(1) == 0 ? new WorldGenCherryTrees(true) : (par1Random.nextInt(2) == 0 ? new WorldGenBigCherryTrees(true) : new WorldGenBigCherryTrees(true))));
    }
}