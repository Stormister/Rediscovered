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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCherryLeaves extends BlockLeavesBase implements IShearable
{
	public static enum LeafCategory
	{
		CAT1;
	}
	
	private static final String[] leaves = new String[] {"cherry"};

	private static final float[] fallingLeavesChance = new float[] {0.1F, 0.008F, 0.016F, 0.008F, 0.0F, 0.008F, 0.016F, 0.1F, 0.008F, 0.1F, 0.008F, 0.1F, 0.008F, 0.008F};

	private Icon[][] textures;
	private final LeafCategory category;
	int[] adjacentTreeBlocks;

	public BlockCherryLeaves(int id, LeafCategory cat)
	{
        super(id, Material.leaves, false);
		category = cat;
		this.setHardness(0.2F);
		this.setStepSound(Block.soundGrassFootstep);
		this.setTickRandomly(true);
		this.setLightOpacity(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[3][leaves.length];
			for (int i = 0; i < leaves.length; ++i)
			{
				textures[0][i] = iconRegister.registerIcon(mod_Rediscovered.modid + ":leaves_" + leaves[i]);
				textures[1][i] = iconRegister.registerIcon(mod_Rediscovered.modid + ":leaves_" + leaves[i] + "_opaque");
			}
	}

	public Icon getIconFallingLeaves(int metadata)
	{
		int type = getTypeFromMeta(metadata) + (category.ordinal() * 4);
		return textures[1][type >= leaves.length ? 0 : type];
	}

	public float getSpawnChanceFallingLeaves(int metadata)
	{
		int type = getTypeFromMeta(metadata) + (category.ordinal() * 4);
		return fallingLeavesChance[type >= leaves.length ? 0 : type];
	}

	@Override
	public Icon getIcon(int side, int metadata)
	{
		int type = getTypeFromMeta(metadata) + (category.ordinal() * 4);
		return textures[(!isOpaqueCube() ? 0 : 1)][type >= leaves.length ? 0 : type];
	}

	public boolean isOpaqueCube()
	{
		return Block.leaves.isOpaqueCube();
	}

	@Override
	@SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)par3 - 0.05D;
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            par1World.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

	public boolean isType(int metadata, int baseMeta)
	{
		for (int type = baseMeta; type <= baseMeta + (4 * 3); type += 4)
		{
			if (metadata == type) return true;
		}

		return false;
	}

    @Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        byte radius = 1;
        int bounds = radius + 1;

        if (world.checkChunksExist(x - bounds, y - bounds, z - bounds, x + bounds, y + bounds, z + bounds)) 
        {
            for (int i = -radius; i <= radius; ++i) 
            {
                for (int j = -radius; j <= radius; ++j) 
                {
                    for (int k = -radius; k <= radius; ++k)
                    {
						int block = world.getBlockId(x + i, y + j, z + k);

						if (Block.blocksList[block]  != null) 
						{
							Block.blocksList[block].beginLeavesDecay(world, x + i, y + j, z + k);
						}
                    }
                }
            }
        }
    }

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.isRemote)
			return;

		int meta = world.getBlockMetadata(x, y, z);

		if ((meta & 8) != 0 && (meta & 4) == 0)
		{
			byte b0 = 4;
			int i1 = b0 + 1;
			byte b1 = 32;
			int j1 = b1 * b1;
			int k1 = b1 / 2;

			if (adjacentTreeBlocks == null)
			{
				adjacentTreeBlocks = new int[b1 * b1 * b1];
			}

			int l1;

			if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
			{
				int i2;
				int j2;
				int k2;

				for (l1 = -b0; l1 <= b0; ++l1)
				{
					for (i2 = -b0; i2 <= b0; ++i2)
					{
						for (j2 = -b0; j2 <= b0; ++j2)
						{
                            int block = world.getBlockId(x + l1, y + i2, z + j2);

							if (block != 0 && Block.blocksList[block].canSustainLeaves(world, x + l1, y + i2, z + j2))
							{
								adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
							}
							else if (block != 0 && Block.blocksList[block].isLeaves(world, x + l1, y + i2, z + j2))
							{
								adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
							}
							else
							{
								adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
							}
						}
					}
				}

				for (l1 = 1; l1 <= 4; ++l1)
				{
					for (i2 = -b0; i2 <= b0; ++i2)
					{
						for (j2 = -b0; j2 <= b0; ++j2)
						{
							for (k2 = -b0; k2 <= b0; ++k2)
							{
								if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
								{
									if (adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
									}

									if (adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
									{
										adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
									}
								}
							}
						}
					}
				}
			}

			l1 = adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

			if (l1 >= 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
			}
			else
			{
				this.removeLeaves(world, x, y, z);
			}
		}
	}

    private void removeLeaves(World world, int x, int y, int z)
    {
        this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlockToAir(x, y, z);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_Rediscovered.CherrySapling.blockID;
    }

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
	{
		if (world.isRemote)
			return;

		if (world.rand.nextInt(20) == 0)
		{
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(mod_Rediscovered.CherrySapling, 1));
		}

		if (((metadata & 3) == 0 || (metadata & 3) == 4 || (metadata & 3) == 7) && (world.rand.nextInt(50) == 0)) 
		{
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.appleRed.itemID, 1, 8));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return (getTypeFromMeta(meta) + category.ordinal() * 4) + 1;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
    {
		return getTypeFromMeta(world.getBlockMetadata(x, y, z));
	}

	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, getTypeFromMeta(world.getBlockMetadata(x, y, z))));
		return ret;
	}

	public String getLeafType(int metadata)
	{
		int type = getTypeFromMeta(metadata) + (category.ordinal() * 4);
		return leaves[type >= leaves.length ? 0 : type];
	}

	private static int getTypeFromMeta(int meta)
	{
		meta = meta & 3;
		if (meta < 0 || meta >= leaves.length) {
			meta = 0;
		}
		return meta;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z)
	{
		return true;
	}
}