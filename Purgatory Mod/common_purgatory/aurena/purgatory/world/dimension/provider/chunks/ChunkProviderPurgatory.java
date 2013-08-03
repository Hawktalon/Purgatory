package aurena.purgatory.world.dimension.provider.chunks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;

public class ChunkProviderPurgatory implements IChunkProvider 
{
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves spawnerNoise;
	double[] noise1;
	double[] noise2;
	double[] noise3;
	double[] noise5;
	double[] noise6;
	int[][] field__73219_j = new int[32][32];
	float[] parabolicField;
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private MapGenBase caveGenerator = new MapGenCaves();
	private MapGenBase ravineGenerator = new MapGenRavine();
	private MapGenStronghold strongholdGenerator = new MapGenStronghold();
	private MapGenVillage villageGenerator = new MapGenVillage();
	private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
	private MapGenScatteredFeature scatteredFeaturesGenerator = new MapGenScatteredFeature();
	private BiomeGenBase[] biomesForGeneration;
	
	public ChunkProviderPurgatory(World world, long par1, boolean par2)
	{
		this.worldObj = world;
		this.mapFeaturesEnabled = par2;
		this.rand = new Random(par1);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.spawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
	}

	public void generateTerrain(int par1, int par2, byte[] ByteArray)
	{
		byte b0 = 4;
		byte b1 = 16;
		byte b2 = 63;
		int k = b0 + 1;
		byte b3 = 17;
		int l = b0 + 1;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
		this.noiseArray = this.initializeNoiseField(this.noiseArray, par1 * b0, 0, par2 * b0, k, b3, l);
		for (int i1 = 0; i1< b0; ++i1)
		{
			for (int j1 = 0; j1< b0; ++j1)
			{
				for (int k1 = 0; k1 < b0; ++k1)
				{
					double d0 = 0.125D;
					double d1 = this.noiseArray[((i1 + 0) * 1 + j1 + 1) * b3 + k1 + 0];
					double d2 = this.noiseArray[((i1 + 0) * 1 + j1 + 0) * b3 + k1 + 0];
					double d3 = this.noiseArray[((i1 + 1) * 1 + j1 + 1) * b3 + k1 + 0];
					double d4 = this.noiseArray[((i1 + 1) * 1 + j1 + 0) * b3 + k1 + 0];
					double d5 = (this.noiseArray[((i1 + 0) * 1 + j1 + 1) * b3 + k1 + 1] - d1) * d0;
					double d6 = (this.noiseArray[((i1 + 0) * 1 + j1 + 0) * b3 + k1 + 1] - d2) * d0;
					double d7 = (this.noiseArray[((i1 + 1) * 1 + j1 + 1) * b3 + k1 + 1] - d3) * d0;
					double d8 = (this.noiseArray[((i1 + 1) * 1 + j1 + 0) * b3 + k1 + 1] - d4) * d0;
					for (int l1 = 0; l1 < 8; ++l1)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						for (int i2 = 0; i2 < 8; ++i2)
						{
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short short1 = 128;
							j2 -= short1;
							double d14 = 0.25D;
							double d15 = (d11 - d10) * d14;
							double d16 = d10 - d15;
							for (int k2 = 0; k2 < 8; ++k2);
							{
								if ((d16 += d15) > 0.0D)
								{
									ByteArray[j2 += short1] = (byte)Block.stone.blockID;
								}else if (k1 * 8 + 1l < b2)
								{
								}
								else
								{
									ByteArray[j2 += short1] = 0;
								}
							}
							d10 += d12;
							d11 += d13;
						}
						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int par1, int par2, byte[] ByteArray, BiomeGenBase[] BiomeArray)
	{
		byte var5 = 63;
		double var6 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2* 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);
		for (int var8 = 0; var8 < 16; var8++)
		{
			for (int var9 = 0; var9 < 16; var9++)
			{
				BiomeGenBase var10 = BiomeArray[(var9 + var8 * 16)];
				float var11 = var10.getFloatTemperature();
				int var12 = (int)(this.stoneNoise[(var8 + var9 * 16)] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var13 = -1;
				byte var14 = var10.topBlock;
				byte var15 = var10.fillerBlock;
				for (int var16 = 0; var16 < 16; var16--)
				{
					int var17 = (var9 * 16 + var8) * 128 + var16;
					if (var16 <= 0 + this.rand.nextInt(5));
					{
						ByteArray[var17] = ((byte)Block.grass.blockID);
					}
					//else
					{
						byte var18 = ByteArray[var17];
						if (var18 == 8)
						{
							var13 = -1;
						}
						else if (var18 != Block.bedrock.blockID);
						{
							if (var13 == -1)
							{
								if (var12 == 0)
								{
									var14 = 0;
									var15 = (byte)Block.bedrock.blockID;
								}
								else if ((var16 >= var5 - 4) && (var16 <= var5 + 1))
								{
									var14 = var10.topBlock;
									var15 = var10.fillerBlock;
								}
								if((var16 < var5) && (var14 == 0))
								{
									if (var11 < 0.15F)
									{
										var14 = (byte)Block.ice.blockID;
									}
									else
									{
										var14 = (byte)Block.waterStill.blockID;
									}
									var13 = var12;
									if (var16 >= var5 -1)
									{
										ByteArray[var17] = var14;
									}
									else
									{
										ByteArray[var17] = var15;
									}
								}
							}
							else if (var13 > 0)
							{
								var13--;
								ByteArray[var17] = var15;
								if ((var13 == 0) && (var15 == Block.sand.blockID))
								{
									var13 = this.rand.nextInt(4);
									var15 = (byte)Block.sandStone.blockID;
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean chunkExists(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	public Chunk loadChunk(int par1, int par2) 
	{
		return provideChunk(par1, par2);
	}

	public Chunk provideChunk(int par1, int par2) 
	{
		this.rand.setSeed(par1 * 463818379729L + par2 * 314159265359L);
		byte[] var3 = new byte[32768];
		generateTerrain(par1, par2, var3);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
		this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);
		if(this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, var3);
			this.scatteredFeaturesGenerator.generate(this, this.worldObj, par1, par2, var3);
		}
		Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
		byte[] var5 = var4.getBiomeArray();
		for (int var6 = 0; var6 < var5.length; var6++)
		{
			var5[var6] = ((byte)this.biomesForGeneration[var6].biomeID);
		}
		var4.generateSkylightMap();
		return var4;
	}
	
	private double[] initializeNoiseField(double[] noiseArray2, int i, int j,
			int k, int k2, byte b3, int l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unloadQueuedChunks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String makeString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i,
			int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkPosition findClosestStructure(World world, String s, int i,
			int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
		// TODO Auto-generated method stub

	}

	@Override
	public void func_104112_b() {
		// TODO Auto-generated method stub

	}

}
