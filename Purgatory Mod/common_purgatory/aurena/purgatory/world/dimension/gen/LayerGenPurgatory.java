package aurena.purgatory.world.dimension.gen;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class LayerGenPurgatory extends GenLayer
{

	public LayerGenPurgatory(long seed)
	{
		super(seed);
	}
	
	public static GenLayer[] makeTheWorld(long seed)
	{
		GenLayer biomes = new LayerGenBiomePurgatory(1L);
		
		biomes = new GenLayerZoom(1000L, biomes);
		
		GenLayer genlayerzoom = new GenLayerVoronoiZoom(1L, biomes);
		
		biomes.initWorldGenSeed(seed);
		genlayerzoom.initWorldGenSeed(seed);
		return new GenLayer[] {biomes, genlayerzoom};
	}
	
}
