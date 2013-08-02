package aurena.purgatory.worldgen.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenShadowedFields extends BiomeGenBase
{
	
	public BiomeGenShadowedFields(int id)
	{
		super(id);
		this.minHeight = 0.1F;
		this.maxHeight = 0.1F;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.topBlock = ((byte)Block.grass.blockID);
		this.fillerBlock = ((byte)Block.dirt.blockID);
		this.getModdedBiomeGrassColor(0x808080);
		this.getModdedBiomeFoliageColor(0x808080);
		this.waterColorMultiplier = 0x808080;
	}

}
