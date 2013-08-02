package aurena.purgatory.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDuskOre extends Block
{
	
	public BlockDuskOre(int id)
	{
		super(id, Material.rock);
		this.setHardness(5F);
		this.setResistance(10F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void registerIcons(IconRegister iconReg)
	{		
		this.blockIcon = iconReg.registerIcon("purgatory:duskore");
	}

}
