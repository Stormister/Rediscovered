package com.stormister.rediscovered;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockCherrySlab extends BlockSlab
{
	public static enum SlabCategory
	{
		WOOD1;
	}

	private static final String[] woodTypes = new String[] {"cherry"};
	private static final String[] rockTypes = new String[] {"cherry"};
	private IIcon[] textures;

	private final SlabCategory category;

	public BlockCherrySlab(boolean isDoubleSlab, Material material, SlabCategory cat)
	{
		super(isDoubleSlab, material);

		category = cat;
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeWood);
		if (!isDoubleSlab) 
		{
			this.setCreativeTab(CreativeTabs.tabBlock);
		}
		useNeighborBrightness = true;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
			textures = new IIcon[woodTypes.length];

			for (int i = 0; i < woodTypes.length; ++i) 
			{
				textures[i] = iconRegister.registerIcon(mod_Rediscovered.modid + ":planks_"+woodTypes[i]);
			}
		
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return textures[getWoodType(meta)];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		int max = 1;
		for (int i = 0; i < max; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public String func_150002_b(int meta)
	{
		return (new StringBuilder()).append(woodTypes[getWoodType(meta)]).append("Slab").toString();
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
				return Item.getItemFromBlock(mod_Rediscovered.CherrySlab);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		float hardness = blockHardness;
		return hardness;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		float resistance = blockResistance;
		return resistance / 5.0F;
	}

	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		return new ItemStack(mod_Rediscovered.CherrySlab);
	}

	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this, 2, meta);
	}

	private int getWoodType(int meta)
	{
		meta = getTypeFromMeta(meta) + category.ordinal() * 8;
		if (meta < woodTypes.length)
			return meta;

		return 0;
	}

	private static int getTypeFromMeta(int meta)
	{
		return 0;
	}
}