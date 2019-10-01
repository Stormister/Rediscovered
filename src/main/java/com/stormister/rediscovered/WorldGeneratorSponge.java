package com.stormister.rediscovered;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorSponge implements IWorldGenerator {

	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
        switch(world.provider.dimensionId){
            case 0 : generateOcean(world, random,chunkX*16,chunkZ*16);
        }
    }

	private boolean generateOcean(World world, Random rand, int x, int z) 
	{
		int y = rand.nextInt(56);
	    if (y <= 35 || y >= 56) return false;

	    int[][] yAdd = new int[3][3];

	    for (int xx = x - 1; xx <= x + 1; xx++) {
	      for (int zz = z - 1; zz <= z + 1; zz++) {
	        for (int yy = y - 1; yy <= y + 1; yy++) {
	          if ((world.getBlock(xx, yy, zz) == Blocks.water) && (world.getBlock(xx, yy - 1, zz) != Blocks.water)) {
	            yAdd[(xx - x + 1)][(zz - z + 1)] = (yy - y);
	            break;
	          }

	          if (yy == y + 1) return false;
	        }
	      }
	    }

	    if (rand.nextInt(5) <= 3) world.setBlock(x - 1, y + yAdd[0][1], z, mod_Rediscovered.Sponge);
	    if (rand.nextInt(5) <= 3) world.setBlock(x + 1, y + yAdd[2][1], z, mod_Rediscovered.Sponge);
	    if (rand.nextInt(5) <= 3) world.setBlock(x, y + yAdd[1][0], z - 1, mod_Rediscovered.Sponge);
	    if (rand.nextInt(5) <= 3) world.setBlock(x, y + yAdd[1][2], z + 1, mod_Rediscovered.Sponge);
	    world.setBlock(x, y + yAdd[1][1], z, mod_Rediscovered.Sponge);
	    if (rand.nextInt(4) <= 2) world.setBlock(x, y + yAdd[1][1] + 1, z, mod_Rediscovered.Sponge);
	    return true;
	}
}