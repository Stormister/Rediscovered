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



public class BlockDirtSlab extends BlockSlab
{
	public static enum SlabCategory2
	{
		DIRT;
	}

	private static final String[] woodTypes = new String[] {"dirt"};
	private static final String[] rockTypes = new String[] {"dirt"};
	private IIcon[] textures;

	private final SlabCategory2 category;

	public BlockDirtSlab(boolean isDoubleSlab, Material material, SlabCategory2 cat)
	{
		super(isDoubleSlab, material);

		category = cat;
		this.setHardness(0.5F);
		this.setHarvestLevel("shovel", 0);
		this.setStepSound(Block.soundTypeGravel);
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
				textures[i] = iconRegister.registerIcon("minecraft" + ":"+woodTypes[i]);
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
		return Item.getItemFromBlock(mod_Rediscovered.DirtSlab);
	}

	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		return new ItemStack(mod_Rediscovered.DirtSlab);
	}

	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this, 2, meta);
	}

	private int getWoodType(int meta)
	{
		return 0;
	}

	private static int getTypeFromMeta(int meta)
	{
		return 0;
	}
}