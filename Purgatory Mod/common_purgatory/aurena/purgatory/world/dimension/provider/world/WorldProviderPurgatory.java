package aurena.purgatory.world.dimension.provider.world;

import net.minecraft.world.WorldProvider;

public class WorldProviderPurgatory extends WorldProvider
{

	public void registerChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerPurgatory(worldObj.getSeed(), terrainType);
		//TODO IF the sky looks too weird, change this to true
		this.hasNoSky = true;
	}
	
	public String getDimensionName()
	{
		return "Purgatory";
	}
	
}
