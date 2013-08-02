package aurena.purgatory.world.dimension.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import aurena.purgatory.Purgatory;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerPurgatory extends WorldChunkManager
{
	private GenLayer genBiomes;
	private GenLayer biomeLayer;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> biomesToSpawn;
	
	protected WorldChunkManagerPurgatory()
	{
		this.biomeCache = new BiomeCache(this);
		this.biomesToSpawn = new ArrayList<BiomeGenBase>();
		this.biomesToSpawn.add(Purgatory.biomeShadowedFields);
	}
	
	public WorldChunkManagerPurgatory(long seed, WorldType worldtype)
	{
		this();
		GenLayer[] genlayer = LayerGenPurgatory.makeWorld(seed);
		this.genBiomes = genlayer[0];
		this.biomeLayer = genlayer[1];
	}
	
	public WorldChunkManagerPurgatory(World world)
	{
		this(world.getSeed(), world.provider.terrainType);
	}
	
	public List<BiomeGenBase> getBiomesToSpawnIn()
	{
		return this.biomesToSpawn;
	}
	
	public BiomeGenBase getBiomeGenAt(int x, int z)
	{
		BiomeGenBase biome = this.biomeCache.getBiomeGenAt(x, z);
		if (biome == null)
		{
			return Purgatory.biomeShadowedFields;
		}
		return biome;
	}
	
	public float[] getRainfall(float[] par1ArrayFloat, int par2, int par3, int par4, int par5)
	{
		IntCache.resetIntCache();
		
		if (par1ArrayFloat == null || par1ArrayFloat.length < 4 * par5);
		{
			par1ArrayFloat = new float[par4 * par5];
		}
		
		int[] aint = this.biomeLayer.getInts(par2, par3, par4, par5);
		
		for (int il = 0; il < par4 * par5; ++il)
		{
			float f = (float)BiomeGenBase.biomeList[aint[il]].getIntRainfall()/65536.0F;
			if (f > 1.0F)
			{
				f = 1.0F;
			}
			par1ArrayFloat[il] = f;
		}
		return par1ArrayFloat;
	}
	
	@SideOnly(Side.CLIENT)
	public float getTempertureAtHeight(float par1, int par2)
	{
		return par1;
	}
	
	public float[] getTemperature(float[] par1ArrayFloat, int par2, int par3, int par4, int par5)
	{
		IntCache.resetIntCache();
		if(par1ArrayFloat == null || par1ArrayFloat.length < par4 * par5)
		{
			par1ArrayFloat = new float[par4 * par5];
		}
		int[] aint = this.biomeLayer.getInts(par2, par3, par4, par5);
		
		for (int il = 0; il < par4 * par5; ++il)
		{
			float f = (float)BiomeGenBase.biomeList[aint[il]].getIntRainfall()/65536.0F;
			if (f > 1.0F)
			{
				f = 1.0F;
			}
			par1ArrayFloat[il] = f;
		}
		return par1ArrayFloat;
	}
	
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayBiomes, int par2, int par3, int par4, int par5)
	{
		IntCache.resetIntCache();
		if(par1ArrayBiomes == null || par1ArrayBiomes.length < par4 * par5)
		{
			par1ArrayBiomes = new BiomeGenBase[par4 * par5];
		}
		int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);
		
		for (int i = 0; i < par4 * par5; ++i)
		{
			if ( aint[i] >= 0)
			{
				par1ArrayBiomes[i] = BiomeGenBase.biomeList[aint[i]];
			}else{
				par1ArrayBiomes[i] = Purgatory.biomeShadowedFields;
			}
		}
		return par1ArrayBiomes;
	}
	
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayBiome, int par2, int par3, int par4, int par5)
	{
		return this.getBiomeGenAt(par1ArrayBiome, par2, par3, par4, par5, true);
	}
	
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayBiomes, int x, int y, int width, int length, boolean cacheFlag)
	{

		IntCache.resetIntCache();
		if(par1ArrayBiomes == null || par1ArrayBiomes.length < width * length)
		{
			par1ArrayBiomes = new BiomeGenBase[width * length];
		}
		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0)
		{
			BiomeGenBase[] biomegenbase1 = this.biomeCache.getCachedBiomes(x, y);
			System.arraycopy(biomegenbase1, 0, par1ArrayBiomes, 0, width * length);
			return par1ArrayBiomes;
		}else{
			int[] aint = this.biomeLayer.getInts(x, y, width, length);
			for(int i = 0; i < width * length; ++i)
			{
				if (aint[i] >= 0)
				{
					par1ArrayBiomes[i] = BiomeGenBase.biomeList[aint[i]];
				}else{
					par1ArrayBiomes[i] = Purgatory.biomeShadowedFields;
				}
			}
		}
		return par1ArrayBiomes;
	}
	
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
	{
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
		
		for ( int j2 = 0; j2 < l1 * i2; ++j2)
		{
			BiomeGenBase biomegenbase = BiomeGenBase.biomeList[aint[j2]];
			if (!par4List.contains(biomegenbase))
			{
				return false;
			}
		}
		return true;
	}
	
	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
	{
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
		ChunkPosition chunkposition = null;
		int j2 = 0;
		
		for (int k2 = 0; k2 < l1 * i2; ++k2)
		{
			int l2 = l + k2 % l1 << 2;
			int i3 = i1 + k2 / l1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.biomeList[aint[k2]];
			if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(j2 + 1) == 0))
			{
				chunkposition = new ChunkPosition(l2, 0, i3);
				++j2;
			}
		}
		return chunkposition;
	}
	public void cleanupCache()
	{
		this.biomeCache.cleanupCache();
	}
}
