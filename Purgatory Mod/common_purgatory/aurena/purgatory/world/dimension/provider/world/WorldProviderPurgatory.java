package aurena.purgatory.world.dimension.provider.world;

import aurena.purgatory.world.dimension.DimensionIDMulti;
import aurena.purgatory.world.dimension.manager.WorldChunkManagerPurgatory;
import aurena.purgatory.world.dimension.provider.chunks.ChunkProviderPurgatory;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderPurgatory extends WorldProvider
{

	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerPurgatory(worldObj.getSeed(), terrainType);
		this.hasNoSky = true;
	}
	
	public String getDimensionName()
	{
		return "Purgatory Realm";
	}
	
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.getProvider(DimensionIDMulti.DIMID_1);
	}
	
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderPurgatory(worldObj, worldObj.getSeed(), true);
	}
	
	public boolean canRespawnHere()
	{
		return true;
	}

	public int getReturnDimension(EntityPlayerMP player)
	{
		return DimensionIDMulti.DIMID_1;
	}
	
}
	
