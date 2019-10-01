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
import java.io.PrintStream;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
 
public class WorldGenSkySpawn extends WorldGenerator
{
   public WorldGenSkySpawn()
   {
     
   }
   
   public boolean generate(World world, Random rand, int x, int y, int z)
	  {
		   if(world.getBlockId(x, y, z)== Block.planks.blockID || world.getBlockId(x, y, z)== Block.wood.blockID || world.getBlockId(x, y, z)== Block.cloth.blockID || world.getBlockId(x, y, z)== Block.cobblestone.blockID)
		   {
		      return false;
		   }
	   
		   	int planks = Block.planks.blockID;
		   	int bed;
			   if(mod_Rediscovered.DreamBedEnabled)
				   bed = mod_Rediscovered.DreamBed.blockID;
			   else
				   bed = Block.bed.blockID;
            int chair = mod_Rediscovered.Chair.blockID;
            int table = mod_Rediscovered.Table.blockID;
            int crafting = Block.workbench.blockID;
            int furnace = Block.furnaceIdle.blockID;
            int torch = Block.torchWood.blockID;
            int glass = Block.glass.blockID;
            int stone = Block.cobblestone.blockID;
            int door = Block.doorWood.blockID;
            int wool = Block.cloth.blockID;
            int wood = Block.wood.blockID;
            int blue = Block.blockLapis.blockID;
            int grass = Block.grass.blockID;
            int dirt = Block.dirt.blockID;
            int brick = Block.stoneBrick.blockID;
            int fence = Block.fenceIron.blockID;
            int netherrack = Block.netherrack.blockID;
            int fire = Block.fire.blockID;
           
            
            //Air
            for(int a = 1; a <= 5; a++)
            	for(int b = 0; b <= 4; b++)
            		for(int c = -1; c <= 3; c++)
            			world.setBlock(x + a, y + b, z + c, 0);
            
           
            //Grass
            //Front
            world.setBlock(x+7, y - 1 , z-4, grass);
            world.setBlock(x+7, y - 1 , z-3, grass);
            world.setBlock(x+7, y - 1 , z-2, grass);
            world.setBlock(x+7, y - 1 , z-1, grass);
            world.setBlock(x+7, y - 1 , z, grass);
            world.setBlock(x+7, y - 1 , z+1, grass);
            world.setBlock(x+7, y - 1 , z+2, grass);
            world.setBlock(x+7, y - 1 , z+3, grass);
            world.setBlock(x+7, y - 1 , z+4, grass);
            world.setBlock(x+7, y - 1 , z+5, grass);
            
            //Right Side
            world.setBlock(x-1, y - 1 , z-4, grass);
            world.setBlock(x, y - 1 , z-4, grass);
            world.setBlock(x+1, y - 1 , z-4, grass);
            world.setBlock(x+2, y - 1 , z-4, grass);
            world.setBlock(x+3, y - 1 , z-4, grass);
            world.setBlock(x+4, y - 1 , z-4, grass);
            world.setBlock(x+5, y - 1 , z-4, grass);
            world.setBlock(x+6, y - 1 , z-4, grass);
            world.setBlock(x+7, y - 1 , z-4, grass);

            world.setBlock(x-1, y - 1 , z-3, grass);
            world.setBlock(x, y - 1 , z-3, grass);
            world.setBlock(x+1, y - 1 , z-3, grass);
            world.setBlock(x+2, y - 1 , z-3, grass);
            world.setBlock(x+3, y - 1 , z-3, grass);
            world.setBlock(x+4, y - 1 , z-3, grass);
            world.setBlock(x+5, y - 1 , z-3, grass);
            world.setBlock(x+6, y - 1 , z-3, grass);
            world.setBlock(x+7, y - 1 , z-3, grass);
            
            //Left Side
            world.setBlock(x-1, y - 1 , z+5, grass);
            world.setBlock(x, y - 1 , z+5, grass);
            world.setBlock(x+1, y - 1 , z+5, grass);
            world.setBlock(x+2, y - 1 , z+5, grass);
            world.setBlock(x+3, y - 1 , z+5, grass);
            world.setBlock(x+4, y - 1 , z+5, grass);
            world.setBlock(x+5, y - 1 , z+5, grass);
            world.setBlock(x+6, y - 1 , z+5, grass);
            world.setBlock(x+7, y - 1 , z+5, grass);
            
            //Back
            world.setBlock(x - 1, y - 1 , z-4, grass);
            world.setBlock(x - 1, y - 1 , z-3, grass);
            world.setBlock(x - 1, y - 1 , z-2, grass);
            world.setBlock(x - 1, y - 1 , z-1, grass);
            world.setBlock(x - 1, y - 1 , z, grass);
            world.setBlock(x - 1, y - 1 , z+1, grass);
            world.setBlock(x - 1, y - 1 , z+2, grass);
            world.setBlock(x - 1, y - 1 , z+3, grass);
            world.setBlock(x - 1, y - 1 , z+4, grass);
            world.setBlock(x - 1, y - 1 , z+5, grass);
            
            
            
            //Ground
            world.setBlock(x + 6, y -2, z-2, dirt);
            world.setBlock(x + 5, y -2, z-2, dirt);
            world.setBlock(x + 4, y -2, z-2, dirt);
            world.setBlock(x + 3, y -2, z-2, dirt);
            world.setBlock(x + 2, y -2, z-2, dirt);
            world.setBlock(x + 1, y -2, z-2, dirt);
            world.setBlock(x, y -2, z-2, dirt);
            world.setBlock(x + 6, y -2, z-1, dirt);
            world.setBlock(x + 5, y -2, z-1, dirt);
            world.setBlock(x + 4, y -2, z-1, dirt);
            world.setBlock(x + 3, y -2, z-1, dirt);
            world.setBlock(x + 2, y -2, z-1, dirt);
            world.setBlock(x + 1, y -2, z-1, dirt);
            world.setBlock(x, y -2, z-1, dirt);
            world.setBlock(x + 6, y -2, z, dirt);
            world.setBlock(x + 5, y -2, z, dirt);
            world.setBlock(x + 4, y -2, z, dirt);
            world.setBlock(x + 3, y -2, z, dirt);
            world.setBlock(x + 2, y -2, z, dirt);
            world.setBlock(x + 1, y -2, z, dirt);
            world.setBlock(x, y -2, z, dirt);
            world.setBlock(x + 6, y -2, z+1, dirt);
            world.setBlock(x + 5, y -2, z+1, dirt);
            world.setBlock(x + 4, y -2, z+1, dirt);
            world.setBlock(x + 3, y -2, z+1, dirt);
            world.setBlock(x + 2, y -2, z+1, dirt);
            world.setBlock(x + 1, y -2, z+1, dirt);
            world.setBlock(x, y -2, z+1, dirt);
            world.setBlock(x + 6, y -2, z+2, dirt);
            world.setBlock(x + 5, y -2, z+2, dirt);
            world.setBlock(x + 4, y -2, z+2, dirt);
            world.setBlock(x + 3, y -2, z+2, dirt);
            world.setBlock(x + 2, y -2, z+2, dirt);
            world.setBlock(x + 1, y -2, z+2, dirt);
            world.setBlock(x, y -2, z+2, dirt);
            world.setBlock(x + 6, y -2, z+3, dirt);
            world.setBlock(x + 5, y -2, z+3, dirt);
            world.setBlock(x + 4, y -2, z+3, dirt);
            world.setBlock(x + 3, y -2, z+3, dirt);
            world.setBlock(x + 2, y -2, z+3, dirt);
            world.setBlock(x + 1, y -2, z+3, dirt);
            world.setBlock(x, y -2, z+3, dirt);
            world.setBlock(x + 6, y -2, z+4, dirt);
            world.setBlock(x + 5, y -2, z+4, dirt);
            world.setBlock(x + 4, y -2, z+4, dirt);
            world.setBlock(x + 3, y -2, z+4, dirt);
            world.setBlock(x + 2, y -2, z+4, dirt);
            world.setBlock(x + 1, y -2, z+4, dirt);
            world.setBlock(x, y -2, z+4, dirt);
           
            
            
            // Bottom Ring
           
            world.setBlock(x, y , z, planks);
            world.setBlock(x, y , z + 1, planks);
            world.setBlock(x, y , z + 2, planks);
            world.setBlock(x, y, z + 3, planks);
            world.setBlock(x, y, z + 4, wood);
            world.setBlock(x + 1, y, z + 4, planks);
            world.setBlock(x + 2, y, z + 4, planks);
            world.setBlock(x + 3, y , z + 4, planks);
            world.setBlock(x + 4, y, z + 4, planks);
            world.setBlock(x + 5, y, z + 4, planks);
            world.setBlock(x + 6, y , z + 4, wood);
            world.setBlock(x + 6, y, z + 3, planks);
            world.setBlock(x + 6, y, z + 2, planks);
            world.setBlock(x + 6, y, z , planks);
            world.setBlock(x + 6, y, z - 1, planks);
            world.setBlock(x + 6, y, z - 2, wood);
            world.setBlock(x + 5, y, z - 2, planks);
            world.setBlock(x + 4, y, z - 2, planks);
            world.setBlock(x + 3, y, z - 2, planks);
            world.setBlock(x + 2, y, z - 2, planks);
            world.setBlock(x + 1, y, z - 2, planks);
            world.setBlock(x , y, z - 2, wood);
            world.setBlock(x , y, z - 1, planks);
            world.setBlock(x , y, z , planks);
           
            // Floor
           
            world.setBlock(x, y -1 , z, planks);
            world.setBlock(x, y -1 , z + 1, planks);
            world.setBlock(x, y - 1, z + 2, planks);
            world.setBlock(x, y -1 , z + 3, planks);
            world.setBlock(x, y - 1, z + 4, wood);
            world.setBlock(x + 1, y - 1, z + 4, planks);
            world.setBlock(x + 2, y - 1, z + 4, planks);
            world.setBlock(x + 3, y -1 , z + 4, planks);
            world.setBlock(x + 4, y - 1, z + 4, planks);
            world.setBlock(x + 5, y -1 , z + 4, planks);
            world.setBlock(x + 6, y - 1, z + 4, wood);
            world.setBlock(x + 6, y -1, z + 3, planks);
            world.setBlock(x + 6, y -1, z + 2, planks);
            world.setBlock(x + 6, y -1, z + 1, planks);
            world.setBlock(x + 6, y -1, z , planks);
            world.setBlock(x + 6, y -1, z - 1, planks);
            world.setBlock(x + 6, y -1, z - 2, wood);
            world.setBlock(x + 5, y -1, z - 2, planks);
            world.setBlock(x + 1, y -1, z - 2, planks);
            world.setBlock(x , y -1, z - 2, wood);
            world.setBlock(x , y -1, z - 1, planks);
            world.setBlock(x , y -1, z , planks);
            world.setBlock(x + 1 , y -1, z , planks);
            world.setBlock(x + 2, y -1, z , planks);
            world.setBlock(x  + 3, y -1, z , planks);
            world.setBlock(x  + 4, y -1, z , planks);
            world.setBlock(x + 5, y -1, z , planks);
            world.setBlock(x + 6, y -1, z , planks);
            world.setBlock(x + 1 , y -1, z + 1 , planks);
            world.setBlock(x + 2, y -1, z + 1, planks);
            world.setBlock(x  + 3, y -1, z + 1 , planks);
            world.setBlock(x  + 4, y -1, z + 1, planks);
            world.setBlock(x + 5, y -1, z + 1, planks);
            world.setBlock(x + 6, y -1, z + 1, planks);
           
           
            world.setBlock(x + 1 , y -1, z + 2 , planks);
            world.setBlock(x + 2, y -1, z + 2, planks);
            world.setBlock(x  + 3, y -1, z + 2 , planks);
            world.setBlock(x  + 4, y -1, z + 2, planks);
            world.setBlock(x + 5, y -1, z + 2, planks);
            world.setBlock(x + 6, y -1, z + 2, planks);
           
           
            world.setBlock(x + 1 , y -1, z + 3 , planks);
            world.setBlock(x + 2, y -1, z + 3, planks);
            world.setBlock(x  + 3, y -1, z + 3 , planks);
            world.setBlock(x  + 4, y -1, z + 3, planks);
            world.setBlock(x + 5, y -1, z + 3, planks);
            world.setBlock(x + 6, y -1, z + 3, planks);

            world.setBlock(x + 1 , y -1, z + 4 , planks);
            world.setBlock(x + 2, y -1, z + 4, planks);
            world.setBlock(x  + 3, y -1, z + 4 , planks);
            world.setBlock(x  + 4, y -1, z + 4, planks);
            world.setBlock(x + 5, y -1, z + 4, planks);
            world.setBlock(x + 6, y -1, z + 4, planks);

            world.setBlock(x + 1 , y -1, z - 1 , planks);
            world.setBlock(x + 2, y -1, z - 1, planks);
            world.setBlock(x  + 3, y -1, z - 1 , planks);
            world.setBlock(x  + 4, y -1, z -1, planks);
            world.setBlock(x + 5, y -1, z - 1, planks);
            world.setBlock(x + 6, y -1, z - 1, planks);
           
            world.setBlock(x + 1 , y -1, z - 2 , planks);
            world.setBlock(x + 2, y -1, z - 2, planks);
            world.setBlock(x  + 3, y -1, z - 2 , planks);
            world.setBlock(x  + 4, y -1, z -2, planks);
            world.setBlock(x + 5, y -1, z - 2, planks);
            world.setBlock(x + 6, y -1, z - 2, planks);
            
            
            //Carpet
            world.setBlock(x + 4, y -1, z, wool);
            world.setBlock(x + 3, y -1, z, wool);
            world.setBlock(x + 2, y -1, z, wool);
            world.setBlock(x + 4, y -1, z+1, wool);
            world.setBlock(x + 3, y -1, z+1, wool);
            world.setBlock(x + 2, y -1, z+1, wool);
            world.setBlock(x + 4, y -1, z+2, wool);
            world.setBlock(x + 3, y -1, z+2, wool);
            world.setBlock(x + 2, y -1, z+2, wool);

           
            //Contents of house
            world.setBlock(x + 2, y, z + 3, bed, 1, 3);
            world.setBlock(x + 1, y, z + 3, bed, 1 + 8, 3);
            world.setBlock(x + 1, y, z + 2, table);
            world.setBlock(x + 1, y, z - 1, chair, 0, 3);


            //Second Ring

                    world.setBlock(x, y + 1 , z, planks);
                    world.setBlock(x, y + 1, z + 1, glass);
                    world.setBlock(x, y + 1, z + 2, planks);
                    world.setBlock(x, y + 1, z + 3, planks);
                    world.setBlock(x, y + 1, z + 4, wood);
                    world.setBlock(x + 1, y + 1, z + 4, planks);
                    world.setBlock(x + 2, y + 1, z + 4, planks);
                    world.setBlock(x + 3, y + 1, z + 4, glass);
                    world.setBlock(x + 4, y + 1, z + 4, planks);
                    world.setBlock(x + 5, y + 1, z + 4, planks);
                    world.setBlock(x + 6, y + 1, z + 4, wood);
                    world.setBlock(x + 6, y + 1, z + 3, planks);
                    world.setBlock(x + 6, y + 1, z + 2, planks);
                    
                    
                    
                    
                    //door
                    world.setBlock(x + 6, y, z + 1, door, 3, 3);
                    world.setBlock(x + 6, y+1, z + 1, door, 3 + 8, 3);
                    
                    
                    
                    
                    world.setBlock(x + 6, y + 1, z , planks);
                    world.setBlock(x + 6, y + 1, z - 1, planks);
                    world.setBlock(x + 6, y + 1, z - 2, wood);
                    world.setBlock(x + 5, y + 1, z - 2, planks);
                    world.setBlock(x + 4, y + 1, z - 2, planks);
                    world.setBlock(x + 2, y + 1, z - 2, planks);
                    world.setBlock(x + 1, y + 1, z - 2, planks);
                    world.setBlock(x , y + 1, z - 2, wood);
                    world.setBlock(x , y + 1, z - 1, planks);
                    world.setBlock(x , y + 1, z , planks);

                    //Third Ring
                   
                    world.setBlock(x, y + 2 , z, planks);
                    world.setBlock(x, y + 2, z + 1, glass);
                    world.setBlock(x, y + 2, z + 2, planks);
                    world.setBlock(x, y + 2, z + 3, planks);
                    world.setBlock(x, y + 2, z + 4, wood);
                    world.setBlock(x + 1, y + 2, z + 4, planks);
                    world.setBlock(x + 2, y + 2, z + 4, planks);
                    world.setBlock(x + 3, y + 2, z + 4, glass);
                    world.setBlock(x + 4, y + 2, z + 4, planks);
                    world.setBlock(x + 5, y + 2, z + 4, planks);
                    world.setBlock(x + 6, y + 2, z + 4, wood);
                    world.setBlock(x + 6, y + 2, z + 3, planks);
                    world.setBlock(x + 6, y + 2, z + 2, planks);
                    world.setBlock(x + 6, y + 2, z + 1, planks);
                    world.setBlock(x + 6, y + 2, z , planks);
                    world.setBlock(x + 6, y + 2, z - 1, planks);
                    world.setBlock(x + 6, y + 2, z - 2, wood);
                    world.setBlock(x + 5, y + 2, z - 2, planks);
                    world.setBlock(x + 4, y + 2, z - 2, planks);
                    world.setBlock(x + 3, y + 2, z - 2, glass);
                    world.setBlock(x + 2, y + 2, z - 2, planks);
                    world.setBlock(x + 1, y + 2, z - 2, planks);
                    world.setBlock(x , y + 2, z - 2, wood);
                    world.setBlock(x , y + 2, z - 1, planks);
                    world.setBlock(x , y + 2, z , planks);
           
                    //Roof
                   
                    world.setBlock(x + 1 , y +3, z + 2 , stone);
                    world.setBlock(x, y + 3, z + 2 , stone);
                    world.setBlock(x + 6 , y +3, z + 2 , stone);
                    world.setBlock(x + 2, y + 4, z + 2, stone);
                    world.setBlock(x  + 3, y + 5, z + 2 , stone);
                    world.setBlock(x  + 4, y  + 4, z + 2, stone);
                    world.setBlock(x + 5, y + 3, z + 2, stone);
                    world.setBlock(x + 6, y -1, z + 2, planks);
                   
                   
                    world.setBlock(x + 1 , y + 3, z + 3 , stone);
                    world.setBlock(x + 2, y + 4, z + 3, stone);
                    world.setBlock(x, y + 3, z + 3 , stone);
                    world.setBlock(x + 6 , y +3, z + 3 , stone);
                    world.setBlock(x  + 3, y + 5, z + 3 , stone);
                    world.setBlock(x  + 4, y + 4, z + 3, stone);
                    world.setBlock(x + 5, y + 3, z + 3, stone);
                    world.setBlock(x + 6, y -1, z + 3, planks);
       
                    world.setBlock(x + 1 , y + 3, z + 4 , stone);
                    world.setBlock(x + 2, y + 4, z + 4, stone);
                    world.setBlock(x + 2, y + 3, z + 4, stone);
                    world.setBlock(x  + 3, y + 5, z + 4 , stone);
                    world.setBlock(x  + 3, y + 4, z + 4 , stone);
                    world.setBlock(x  + 3, y + 3, z + 4 , stone);
                    world.setBlock(x  + 4, y + 4, z + 4, stone);
                    world.setBlock(x  + 4, y + 3, z + 4, stone);
                    world.setBlock(x + 5, y + 3, z + 4, stone);
                    world.setBlock(x, y + 3, z + 4 , stone);
                    world.setBlock(x + 6 , y +3, z + 4 , stone);
       
                    world.setBlock(x + 1 , y + 3, z - 1 , stone);
                    world.setBlock(x + 2, y + 4, z - 1, stone);
                    world.setBlock(x  + 3, y + 5, z - 1 , stone);
                    world.setBlock(x  + 4, y + 4, z -1, stone);
                    world.setBlock(x + 5, y + 3, z - 1, stone);
                    world.setBlock(x, y + 3, z - 1 , stone);
                    world.setBlock(x + 6 , y +3, z -1 , stone);
                   
                    world.setBlock(x + 1 , y + 3, z - 2 , stone);
                    world.setBlock(x + 2, y + 4, z - 2, stone);
                    world.setBlock(x + 2, y + 3, z - 2, stone);
                    world.setBlock(x  + 3, y + 5, z - 2 , stone);
                    world.setBlock(x  + 3, y + 4, z - 2 , stone);
                    world.setBlock(x  + 3, y + 3, z - 2 , stone);
                    world.setBlock(x  + 4, y + 4, z -2, stone);
                    world.setBlock(x  + 4, y + 3, z -2, stone);
                    world.setBlock(x + 5, y + 3, z - 2, stone);
                    world.setBlock(x, y + 3, z -2 , stone);
                    world.setBlock(x + 6 , y +3, z - 2 , stone);
                   
                   
                    world.setBlock(x + 1 , y + 3, z , stone);
                    world.setBlock(x + 2, y + 4, z, stone);
                    world.setBlock(x  + 3, y + 5, z  , stone);
                    world.setBlock(x  + 4, y + 4, z , stone);
                    world.setBlock(x + 5, y + 3, z , stone);
                    world.setBlock(x, y + 3, z  , stone);
                    world.setBlock(x + 6 , y +3, z  , stone);
                   
                    world.setBlock(x + 1 , y + 3, z + 1 , stone);
                    world.setBlock(x + 2, y + 4, z + 1, stone);
                    world.setBlock(x  + 3, y + 5, z + 1 , stone);
                    world.setBlock(x  + 4, y + 4, z + 1, stone);
                    world.setBlock(x + 5, y + 3, z + 1, stone);
                    world.setBlock(x, y + 3, z + 1 , stone);
                     world.setBlock(x + 6 , y +3, z + 1 , stone);
                     
                     
                     
                     //Fireplace
                     //Fence
                     world.setBlock(x+2, y, z-1, fence);
                     world.setBlock(x+3, y, z-1, fence);
                     world.setBlock(x+4, y, z-1, fence);
                     //Front
                     world.setBlock(x+3, y-1, z-2, netherrack);
                     world.setBlock(x+3, y, z-2, fire);
                     world.setBlock(x+2, y, z-2, brick);
                     world.setBlock(x+4, y, z-2, brick);
                     world.setBlock(x+2, y+1, z-2, brick);
                     world.setBlock(x+4, y+1, z-2, brick);
                     world.setBlock(x+2, y+2, z-2, brick);
                     world.setBlock(x+3, y+2, z-2, brick);
                     world.setBlock(x+4, y+2, z-2, brick);
                     //back
                     world.setBlock(x+2, y, z-3, brick);
                     world.setBlock(x+3, y, z-3, brick);
                     world.setBlock(x+4, y, z-3, brick);
                     world.setBlock(x+2, y+1, z-3, brick);
                     world.setBlock(x+3, y+1, z-3, brick);
                     world.setBlock(x+4, y+1, z-3, brick);
                     world.setBlock(x+2, y+2, z-3, brick);
                     world.setBlock(x+3, y+2, z-3, brick);
                     world.setBlock(x+4, y+2, z-3, brick);
                     
                     world.setBlock(x+3, y+3, z-3, brick);
                     world.setBlock(x+3, y+4, z-3, brick);
                     world.setBlock(x+3, y+5, z-3, brick);
                     world.setBlock(x+3, y+6, z-3, brick);
                     

	return true;
	
	}
}