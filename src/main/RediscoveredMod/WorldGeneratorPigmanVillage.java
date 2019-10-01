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

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorPigmanVillage implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		if (world.provider.dimensionId == mod_Rediscovered.DimID && mod_Rediscovered.EnablePigmanVillages) 
		{
				generateSky(world, random, chunkX * 16, chunkZ * 16);
		}

	}

	public void generateSky(World world, Random rand, int y, int z) {

		int RandPosX = y + rand.nextInt(16);
		int RandPosY = rand.nextInt(150) + 50;
		int RandPosZ = z + rand.nextInt(16);
		(new WorldGenSmallPigmanVillage()).generate(world, rand, RandPosX, RandPosY, RandPosZ);

	}

}