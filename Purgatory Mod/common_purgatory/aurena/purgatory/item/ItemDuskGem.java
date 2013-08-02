package aurena.purgatory.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemDuskGem extends Item
{

	public ItemDuskGem(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	public void registerIcons(IconRegister iconReg)
	{
		this.itemIcon = iconReg.registerIcon("purgatory:duskgem");
	}
	
}
