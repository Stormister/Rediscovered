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
	    if (y <= 41 || y >= 56) return false;

	    int[][] yAdd = new int[3][3];

	    for (int xx = x - 1; xx <= x + 1; xx++) {
	      for (int zz = z - 1; zz <= z + 1; zz++) {
	        for (int yy = y - 1; yy <= y + 1; yy++) {
	          if ((world.getBlockId(xx, yy, zz) == Block.waterStill.blockID) && (world.getBlockId(xx, yy - 1, zz) != Block.waterStill.blockID)) {
	            yAdd[(xx - x + 1)][(zz - z + 1)] = (yy - y);
	            break;
	          }

	          if (yy == y + 1) return false;
	        }
	      }
	    }

	    if (rand.nextInt(5) <= 3) world.setBlock(x - 1, y + yAdd[0][1], z, mod_Rediscovered.TrickSponge.blockID);
	    if (rand.nextInt(5) <= 3) world.setBlock(x + 1, y + yAdd[2][1], z, mod_Rediscovered.TrickSponge.blockID);
	    if (rand.nextInt(5) <= 3) world.setBlock(x, y + yAdd[1][0], z - 1, mod_Rediscovered.TrickSponge.blockID);
	    if (rand.nextInt(5) <= 3) world.setBlock(x, y + yAdd[1][2], z + 1, mod_Rediscovered.TrickSponge.blockID);
	    world.setBlock(x, y + yAdd[1][1], z, mod_Rediscovered.TrickSponge.blockID);
	    if (rand.nextInt(4) <= 2) world.setBlock(x, y + yAdd[1][1] + 1, z, mod_Rediscovered.TrickSponge.blockID);
	    return true;
	}
}