package aurena.purgatory;

import aurena.purgatory.block.BlockDuskOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid= "purgatory", name = "Purgatory Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Purgatory 
{
	@SidedProxy(clientSide = "aurena.purgatory.client.ClientProxy", serverSide = "aurena.purgatory.CommonProxy")
	
	public static CommonProxy proxy;
	
	public static final String modid = "purgatory";
	
	@Instance("Purgatory")
	
	//Block List
	
	public static Block oreDusk;
	
	//Item List
	
	public static Item gemDusk;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent eventPre)
	{
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
		//Blocks
		
		//Ores
		
		oreDusk = new BlockDuskOre(500).setUnlocalizedName("oreDusk");
		
		//Block Registry
		
		GameRegistry.registerBlock(oreDusk, oreDusk.getUnlocalizedName());
		
		//Block Naming
		
		LanguageRegistry.addName(oreDusk, "Dusk Ore");
		
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent eventPost)
	{
		
	}
	
}
