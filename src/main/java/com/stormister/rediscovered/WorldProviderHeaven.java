package com.stormister.rediscovered;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
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
		this.worldChunkMgr = new WorldChunkManagerHell(mod_Rediscovered.heaven, this.dimensionId);
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