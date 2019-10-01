package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockCherrySlab extends ItemSlab
{
	public ItemBlockCherrySlab(Block block, BlockCherrySlab singleSlab, BlockCherrySlab doubleSlab) 
	{
		super(block, singleSlab, doubleSlab, block == doubleSlab);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 7;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) 
	{
		BlockCherrySlab slab = (BlockCherrySlab)Block.getBlockFromItem(itemStack.getItem());
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(slab.func_150002_b(itemStack.getItemDamage())).toString();
	}
}