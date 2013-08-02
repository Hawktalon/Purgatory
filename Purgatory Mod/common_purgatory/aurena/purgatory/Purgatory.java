package aurena.purgatory;

import aurena.purgatory.block.BlockDuskOre;
import aurena.purgatory.item.ItemDuskGem;
import aurena.purgatory.worldgen.biome.BiomeGenShadowedFields;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
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
	
	//Biome List
	
	public static BiomeGenBase biomeShadowedFields;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent eventPre)
	{
		
	}
	//TODO Change all Item and Block ID's to free ID's according to the ID List
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
		
		//Items
		
		//Ore Items
		
		gemDusk = new ItemDuskGem(9001).setUnlocalizedName("gemDusk");
		
		//Item Naming
		
		LanguageRegistry.addName(gemDusk, "Dusk Gem");
		
		//Biomes
		
		biomeShadowedFields = new BiomeGenShadowedFields(58);
		
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent eventPost)
	{
		
	}
	
}
