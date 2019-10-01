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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderHeaven extends WorldProvider {
	private float[] colorsSunriseSunset = new float[4];

	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(mod_Rediscovered.heaven, this.dimensionId, this.dimensionId);
		this.dimensionId = mod_Rediscovered.DimID;
		this.hasNoSky = false;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderHeaven(this.worldObj, this.worldObj.getSeed());
	}

	public int getAverageGroundLevel() {
		return 200;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean getWorldHasVoidParticles()
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	public double getVoidFogYFactor()
    {
        return 30;
    }

	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return false;
	}

	public String getDimensionName() {
		return "Sky";
	}

	public boolean renderClouds() {
		return true;
	}
	
	public double getMovementFactor()
    {
        return 1.2;
    }

	public float setSunSize() {
		return 10.0F;
	}

	public float setMoonSize() {
		return 8.0F;
	}

	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return true;
	}

	public boolean canRespawnHere() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 0F;
	}

	@SideOnly(Side.CLIENT)
	public String getSunTexture() {
		return "/terrain/sun.png";
	}

	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(50, 75, 0);
	}

	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage() {
		if ((this instanceof WorldProviderHeaven)) {
			return "Falling Asleep...";
		}
		return null;
	}

	public float calculateCelestialAngle(long par1, float par3) {
		return 0.0F;
	}

}