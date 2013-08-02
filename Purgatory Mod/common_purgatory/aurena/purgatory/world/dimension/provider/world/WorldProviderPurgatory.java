package aurena.purgatory.world.dimension.provider.world;

import aurena.purgatory.world.dimension.DimensionIDMulti;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

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
	
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(DimensionIDMulti.DIMID_1);
	}
	
	public String getWelcomeMessage()
	{
		return "Taking your soul to Death's Realm";
	}
	
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderPurgatory(worldObj, worldObj.getSeed(), true);
	}
	
	//TODO Enable change in config
	public boolean canRespawnHere()
	{
		return true;
	}
	
	protected String setUserMessage(String par1String)
	{
		return "Taking your soul to Death's Realm";
	}
	
	public int getRepawnDimension(EntityPlayerMP player)
	{
		return 4;
	}
}
	
