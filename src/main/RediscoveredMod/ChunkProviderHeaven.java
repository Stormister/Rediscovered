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

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderHeaven extends ChunkProviderGenerate implements IChunkProvider
{
    private Random random;
    private NoiseGeneratorOctaves field_28086_k;
    private NoiseGeneratorOctaves field_28085_l;
    private NoiseGeneratorOctaves field_28084_m;
    private NoiseGeneratorOctaves field_28083_n;
    private NoiseGeneratorOctaves field_28082_o;
    public NoiseGeneratorOctaves field_28096_a;
    public NoiseGeneratorOctaves field_28095_b;
    public NoiseGeneratorOctaves field_28094_c;
    private World worldObj;
    private double field_28080_q[];
    private double unusedSandNoise[];
    private double unusedGravelNoise[];
    private double stoneNoise[];
    private MapGenBase caveGen;
    private BiomeGenBase field_28075_v[];
    double field_28093_d[];
    double field_28092_e[];
    double field_28091_f[];
    double field_28090_g[];
    double field_28089_h[];
    int field_28088_i[][];

    public ChunkProviderHeaven(World world, long l)
    {
    	super(world, l, true);
        unusedSandNoise = new double[256];
        unusedGravelNoise = new double[256];
        stoneNoise = new double[256];
        caveGen = new MapGenCaves();
        field_28088_i = new int[32][32];
        worldObj = world;
        random = new Random(l);
        field_28086_k = new NoiseGeneratorOctaves(random, 16);
        field_28085_l = new NoiseGeneratorOctaves(random, 16);
        field_28084_m = new NoiseGeneratorOctaves(random, 8);
        field_28083_n = new NoiseGeneratorOctaves(random, 4);
        field_28082_o = new NoiseGeneratorOctaves(random, 4);
        field_28096_a = new NoiseGeneratorOctaves(random, 10);
        field_28095_b = new NoiseGeneratorOctaves(random, 16);
        field_28094_c = new NoiseGeneratorOctaves(random, 8);
    }

    public void generateTerrain(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
    {
        byte byte0 = 2;
        int k = byte0 + 1;
        worldObj.getClass();
        byte byte1 = 33;
        int l = byte0 + 1;
        field_28080_q = func_28073_a(field_28080_q, i * byte0, 0, j * byte0, k, byte1, l);

        for (int i1 = 0; i1 < byte0; i1++)
        {
            label0:

            for (int j1 = 0; j1 < byte0; j1++)
            {
                int k1 = 0;

                do
                {
                    worldObj.getClass();

                    if (k1 >= 32)
                    {
                        continue label0;
                    }

                    double d = 0.25D;
                    double d1 = field_28080_q[((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 0)];
                    double d2 = field_28080_q[((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 0)];
                    double d3 = field_28080_q[((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 0)];
                    double d4 = field_28080_q[((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 0)];
                    double d5 = (field_28080_q[((i1 + 0) * l + (j1 + 0)) * byte1 + (k1 + 1)] - d1) * d;
                    double d6 = (field_28080_q[((i1 + 0) * l + (j1 + 1)) * byte1 + (k1 + 1)] - d2) * d;
                    double d7 = (field_28080_q[((i1 + 1) * l + (j1 + 0)) * byte1 + (k1 + 1)] - d3) * d;
                    double d8 = (field_28080_q[((i1 + 1) * l + (j1 + 1)) * byte1 + (k1 + 1)] - d4) * d;

                    for (int l1 = 0; l1 < 4; l1++)
                    {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 8; i2++)
                        {
                            worldObj.getClass();
                            worldObj.getClass();
                            int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + l1;
                            worldObj.getClass();
                            char c = '\200';
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 8; k2++)
                            {
                                int l2 = 0;

                                if (d15 > 0.0D)
                                {
                                    l2 = Block.stone.blockID;
                                }

                                abyte0[j2] = (byte)l2;
                                j2 += c;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }

                    k1++;
                }
                while (true);
            }
        }
    }

    public void replaceBlocksForBiome(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
    {
        double d = 0.03125D;
        unusedSandNoise = field_28083_n.generateNoiseOctaves(unusedSandNoise, i * 16, j * 16, 0, 16, 16, 1, d, d, 1.0D);
        unusedGravelNoise = field_28083_n.generateNoiseOctaves(unusedGravelNoise, i * 16, 109, j * 16, 16, 1, 16, d, 1.0D, d);
        stoneNoise = field_28082_o.generateNoiseOctaves(stoneNoise, i * 16, j * 16, 0, 16, 16, 1, d * 2D, d * 2D, d * 2D);

        for (int k = 0; k < 16; k++)
        {
            for (int l = 0; l < 16; l++)
            {
                BiomeGenBase biomegenbase = abiomegenbase[k + l * 16];
                int i1 = (int)(stoneNoise[k + l * 16] / 3D + 3D + random.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte0 = biomegenbase.topBlock;
                byte byte1 = biomegenbase.fillerBlock;
                worldObj.getClass();

                for (int k1 = 127; k1 >= 0; k1--)
                {
                    worldObj.getClass();
                    int l1 = (l * 16 + k) * 128 + k1;
                    byte byte2 = abyte0[l1];

                    if (byte2 == 0)
                    {
                        j1 = -1;
                        continue;
                    }

                    if (byte2 != Block.stone.blockID)
                    {
                        continue;
                    }

                    if (j1 == -1)
                    {
                        if (i1 <= 0)
                        {
                            byte0 = 0;
                            byte1 = (byte)Block.stone.blockID;
                        }

                        j1 = i1;

                        if (k1 >= 0)
                        {
                            abyte0[l1] = byte0;
                        }
                        else
                        {
                            abyte0[l1] = byte1;
                        }

                        continue;
                    }

                    if (j1 <= 0)
                    {
                        continue;
                    }

                    j1--;
                    abyte0[l1] = byte1;

                    if (j1 == 0 && byte1 == Block.sand.blockID)
                    {
                        j1 = random.nextInt(4);
                        byte1 = (byte)Block.sandStone.blockID;
                    }
                }
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int i, int j)
    {
        return provideChunk(i, j);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int i, int j)
    {
        random.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        worldObj.getClass();
        byte abyte0[] = new byte[32768];
        field_28075_v = worldObj.getWorldChunkManager().loadBlockGeneratorData(field_28075_v, i * 16, j * 16, 16, 16);
        generateTerrain(i, j, abyte0, field_28075_v);
        replaceBlocksForBiome(i, j, abyte0, field_28075_v);
        caveGen.generate(this, worldObj, i, j, abyte0);
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        byte abyte1[] = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; k++)
        {
            abyte1[k] = (byte)field_28075_v[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] func_28073_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if (ad == null)
        {
            ad = new double[l * i1 * j1];
        }

        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        field_28090_g = field_28096_a.generateNoiseOctaves(field_28090_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        field_28089_h = field_28095_b.generateNoiseOctaves(field_28089_h, i, k, l, j1, 200D, 200D, 0.5D);
        d *= 2D;
        field_28093_d = field_28084_m.generateNoiseOctaves(field_28093_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_28092_e = field_28086_k.generateNoiseOctaves(field_28092_e, i, j, k, l, i1, j1, d, d1, d);
        field_28091_f = field_28085_l.generateNoiseOctaves(field_28091_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;

        for (int i2 = 0; i2 < l; i2++)
        {
            for (int j2 = 0; j2 < j1; j2++)
            {
                double d2 = (field_28090_g[l1] + 256D) / 512D;

                if (d2 > 1.0D)
                {
                    d2 = 1.0D;
                }

                double d3 = field_28089_h[l1] / 8000D;

                if (d3 < 0.0D)
                {
                    d3 = -d3 * 0.29999999999999999D;
                }

                d3 = d3 * 3D - 2D;

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                d3 /= 8D;
                d3 = 0.0D;

                if (d2 < 0.0D)
                {
                    d2 = 0.0D;
                }

                d2 += 0.5D;
                d3 = (d3 * (double)i1) / 16D;
                l1++;
                double d4 = (double)i1 / 2D;

                for (int k2 = 0; k2 < i1; k2++)
                {
                    double d5 = 0.0D;
                    double d6 = (((double)k2 - d4) * 8D) / d2;

                    if (d6 < 0.0D)
                    {
                        d6 *= -1D;
                    }

                    double d7 = field_28092_e[k1] / 512D;
                    double d8 = field_28091_f[k1] / 512D;
                    double d9 = (field_28093_d[k1] / 10D + 1.0D) / 2D;

                    if (d9 < 0.0D)
                    {
                        d5 = d7;
                    }
                    else if (d9 > 1.0D)
                    {
                        d5 = d8;
                    }
                    else
                    {
                        d5 = d7 + (d8 - d7) * d9;
                    }

                    d5 -= 8D;
                    int l2 = 32;

                    if (k2 > i1 - l2)
                    {
                        double d10 = (float)(k2 - (i1 - l2)) / ((float)l2 - 1.0F);
                        d5 = d5 * (1.0D - d10) + -30D * d10;
                    }

                    l2 = 8;

                    if (k2 < l2)
                    {
                        double d11 = (float)(l2 - k2) / ((float)l2 - 1.0F);
                        d5 = d5 * (1.0D - d11) + -30D * d11;
                    }

                    ad[k1] = d5;
                    k1++;
                }
            }
        }

        return ad;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().getBiomeGenAt(k + 16, l + 16);
        random.setSeed(worldObj.getSeed());
        long l1 = (random.nextLong() / 2L) * 2L + 1L;
        long l2 = (random.nextLong() / 2L) * 2L + 1L;
        random.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getSeed());
        double d = 0.25D;

        if (random.nextInt(4) == 0)
        {
            int i1 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int l4 = random.nextInt(128);
            int i8 = l + random.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(worldObj, random, i1, l4, i8);
        }

        if (random.nextInt(8) == 0)
        {
            int j1 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i5 = random.nextInt(random.nextInt(120) + 8);
            int j8 = l + random.nextInt(16) + 8;

            if (i5 < 64 || random.nextInt(10) == 0)
            {
            }
        }

        for (int k1 = 0; k1 < 8; k1++)
        {
            int j5 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k8 = random.nextInt(128);
            int i13 = l + random.nextInt(16) + 8;
        }

        for (int i2 = 0; i2 < 10; i2++)
        {
            int k5 = k + random.nextInt(16);
            worldObj.getClass();
            int l8 = random.nextInt(128);
            int j13 = l + random.nextInt(16);
            (new WorldGenClay(32)).generate(worldObj, random, k5, l8, j13);
        }

        for (int j2 = 0; j2 < 20; j2++)
        {
            int l5 = k + random.nextInt(16);
            worldObj.getClass();
            int i9 = random.nextInt(128);
            int k13 = l + random.nextInt(16);
            (new WorldGenMinable(Block.dirt.blockID, 32)).generate(worldObj, random, l5, i9, k13);
        }

        for (int k2 = 0; k2 < 10; k2++)
        {
            int i6 = k + random.nextInt(16);
            worldObj.getClass();
            int j9 = random.nextInt(128);
            int l13 = l + random.nextInt(16);
        }

        for (int i3 = 0; i3 < 20; i3++)
        {
            int j6 = k + random.nextInt(16);
            worldObj.getClass();
            int k9 = random.nextInt(128);
            int i14 = l + random.nextInt(16);
        }

        for (int j3 = 0; j3 < 20; j3++)
        {
            int k6 = k + random.nextInt(16);
            worldObj.getClass();
            int l9 = random.nextInt(64);
            int j14 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreIron.blockID, 8)).generate(worldObj, random, k6, l9, j14);
        }

        for (int k3 = 0; k3 < 2; k3++)
        {
            int l6 = k + random.nextInt(16);
            worldObj.getClass();
            int i10 = random.nextInt(32);
            int k14 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreGold.blockID, 8)).generate(worldObj, random, l6, i10, k14);
        }

        for (int l3 = 0; l3 < 8; l3++)
        {
            int i7 = k + random.nextInt(16);
            worldObj.getClass();
            int j10 = random.nextInt(16);
            int l14 = l + random.nextInt(16);
        }

        for (int i4 = 0; i4 < 1; i4++)
        {
            int j7 = k + random.nextInt(16);
            worldObj.getClass();
            int k10 = random.nextInt(16);
            int i15 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(worldObj, random, j7, k10, i15);
        }

        for (int j4 = 0; j4 < 1; j4++)
        {
            int k7 = k + random.nextInt(16);
            worldObj.getClass();
            worldObj.getClass();
            int l10 = random.nextInt(16) + random.nextInt(16);
            int j15 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreLapis.blockID, 6)).generate(worldObj, random, k7, l10, j15);
        }

        d = 0.5D;
        int l7 = 0;

        if (random.nextInt(10) == 0)
        {
            l7++;
        }

        for (int i11 = 0; i11 < l7; i11++)
        {
            int k15 = k + random.nextInt(16) + 8;
            int j18 = l + random.nextInt(16) + 8;
            WorldGenerator worldgenerator = biomegenbase.getRandomWorldGenForTrees(random);
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(worldObj, random, k15, worldObj.getHeightValue(k15, j18), j18);
        }

        for (int j11 = 0; j11 < 2; j11++)
        {
            int l15 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k18 = random.nextInt(128);
            int i21 = l + random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantYellow.blockID)).generate(worldObj, random, l15, k18, i21);
        }

        if (random.nextInt(2) == 0)
        {
            int k11 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i16 = random.nextInt(128);
            int l18 = l + random.nextInt(16) + 8;
            (new WorldGenFlowers(mod_Rediscovered.Rose.blockID)).generate(worldObj, random, k11, i16, l18);
        }

        if (random.nextInt(4) == 0)
        {
            int l11 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int j16 = random.nextInt(128);
            int i19 = l + random.nextInt(16) + 8;
        }

        if (random.nextInt(8) == 0)
        {
            int i12 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k16 = random.nextInt(128);
            int j19 = l + random.nextInt(16) + 8;
        }

        for (int j12 = 0; j12 < 10; j12++)
        {
            int l16 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k19 = random.nextInt(128);
            int j21 = l + random.nextInt(16) + 8;
            (new WorldGenReed()).generate(worldObj, random, l16, k19, j21);
        }

        if (random.nextInt(32) == 0)
        {
            int k12 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i17 = random.nextInt(128);
            int l19 = l + random.nextInt(16) + 8;
        }

        int l12 = 0;

        if (biomegenbase == BiomeGenBase.desert)
        {
            l12 += 10;
        }

        for (int j17 = 0; j17 < l12; j17++)
        {
            int i20 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k21 = random.nextInt(128);
            int k22 = l + random.nextInt(16) + 8;
            (new WorldGenCactus()).generate(worldObj, random, i20, k21, k22);
        }

        for (int k17 = 0; k17 < 50; k17++)
        {
            int j20 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int l21 = random.nextInt(random.nextInt(120) + 8);
            int l22 = l + random.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, random, j20, l21, l22);
        }

        for (int l17 = 0; l17 < 20; l17++)
        {
            int k20 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i22 = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
            int i23 = l + random.nextInt(16) + 8;
        }

        for (int i18 = 0; i18 < 16; i18++)
        {
            for (int l20 = 0; l20 < 16; l20++)
            {
                int j22 = worldObj.getPrecipitationHeight(k + i18, l + l20);
                
                //SNOW HEIGHT
                if (j22 > 64 && j22 < 97)
                {
                    worldObj.setBlock(i18 + k, j22, l20 + l, Block.snow.blockID);
                }
            }
        }

        BlockSand.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    /**
     * Unloads the 100 oldest chunks from memory, due to a bug with chunkSet.add() never being called it thinks the list
     * is always empty and will not remove any chunks.
     */
    public boolean unload100OldestChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k)
    {
        BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(i, k);

        if (biomegenbase == null)
        {
            return null;
        }
        else
        {
            return biomegenbase.getSpawnableList(enumcreaturetype);
        }
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k)
    {
        return null;
    }
}
