package com.stormister.rediscovered;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBlockChair implements ISimpleBlockRenderingHandler
{
	final int renderID;

	public RenderBlockChair(int var1)
	{
		this.renderID = var1;
	}

	public void renderRedEgg(RenderBlocks renderBlocks, IBlockAccess iblockaccess, Block par1Block, int par2, int par3, int par4)
	{
		renderBlocks.overrideBlockTexture = par1Block.getIcon(iblockaccess, par2, par3, par4, 0);

		renderBlocks.setRenderBounds(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
		renderBlocks.renderStandardBlock(par1Block, par2, par3, par4);

		renderBlocks.clearOverrideBlockTexture();
	}

	private final TileEntityChair egg = new TileEntityChair();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -1.5F, -0.5F);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(this.egg, 0.0D, 1.0D, 0.0D, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		this.renderRedEgg(renderer, world, block, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return this.renderID;
	}
}