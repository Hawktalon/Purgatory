package aurena.purgatory;

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

@Mod(modid= "purgatory", name = "Purgatory Mod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Purgatory 
{
	@SidedProxy(clientSide = "aurena.purgatory.client.ClientProxy", serverSide = "aurena.purgatory.CommonProxy")
	
	public static CommonProxy proxy;
	
	public static final String modid = "purgatory";
	
	@Instance("Purgatory")
	
	//Block List
	
	public static Block woodDead;
	
	//Item List
	
	public static Item deathEssence;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent eventPre)
	{
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent eventPost)
	{
		
	}
	
}
