package aurena.purgatory.world.dimension.provider.chunks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
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
					else
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
	
	private double[] initializeNoiseField(double[] NoiseArray, int par2, int par3, int par4, int k, int par6, int par7) 
	{
		if (NoiseArray == null)
		{
			NoiseArray = new double[k * par6 * par7];
		}
		if(this.parabolicField == null)
		{
			this.parabolicField = new float[25];
			for (int var8 = -2; var8 <= 2; var8++)
			{
				for (int var9 = -2; var9 <= 2; var9++)
				{
					float var10 = 10.0F / MathHelper.sqrt_float(var8 * var8 + var9 * var9 + 0.2F);
					this.parabolicField[(var8 + 2 + (var9 + 2) * 5)] = var10;
				}
			}
		}
		double var44 = 648.41200000000003D;
		double var45 = 648.41200000000003D;
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, k, par7, 200.0D, 200.0, 0.5D);
		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, k, par7, 1.121D, 1.121D, 0.5D);
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, k, par6, par7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, k, par6, par7, var44, var45, var44);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, k, par6, par7, var44, var45, var44);
		boolean var12 = false;
		boolean var13 = false;
		for (int var14 = 0; var14 < k; var14++)
		{
			for (int var15 = 0; var15 < par7; var15++)
			{
				float var16 = 0.0F;
				float var17 = 0.0F;
				float var18 = 0.0F;
				byte var19 = 2;
				BiomeGenBase var20 = this.biomesForGeneration[(var14 + 12 + (var15 + 2) * (k +5))];
				for (int var21 = -var19; var21 <= var19; var21++)
				{
					for (int var22 = -var19; var22 < var19; var22++)
					{
						BiomeGenBase var23 = this.biomesForGeneration[(var14 + var21 + 2 + (var15 + var22 + 2) * (k + 5))];
						float var24 = this.parabolicField[(var21 + 2 + (var22 + 2) *  5)] / (var23.minHeight + 2.0F);
						if (var23.minHeight > var20.minHeight)
						{
							var24 /= 2.0F;
						}
						var16 += var23.maxHeight * var24;
						var17 += var23.minHeight * var24;
						var18 += var24;
					}
				}
				var16 /= var18;
				var17 /= var18;
				var16 = var16 * 0.9F + 0.1F;
				var17 = (var17 * 4.0F - 1.0F) / 8.0F;
				double var47 = this.noise6[var13] / 8000.0D;
				if (var47 < 0.0D)
				{
					var47 = -var47 * 0.3D;
				}
				var47 = var47 * 3.0D - 2.0D;
				if (var47 < 0.0D)
				{
					var47 /= 2.0D;
					if (var47 < -1.0D)
					{
						var47 /= 1.4D;
						var47 /= 2.0D;
					}
					else
					{
						if (var47 > 1.0D)
						{
							var47 = 1.0D;
						}
						var47 /= 8.0D;
					}
					var13++;
					for (int var46 = 0; var46 < par6; var46++)
					{
						double var48 = var17;
						double var26 = var16;
						var48 += var47 * 0.2D;var48 = var48 * par6 / 16.0D;
						double var28 = par6 / 16.0D;
						double var30 = 0.0D;
						double var32 = (var46 - var28) * 12.0D * 128.0D / 128.0D / var26;
						if (var32 < 0.0D)
						{
							var32 *= 4.0;
						}
						double var34 = this.noise1[var12] / 512.0D;
						double var36 = this.noise2[var12] / 512.0D;
						double var38 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;
						if (var38 < 0.0D)
						{
							var30 = var34;
						}
						else if (var38 > 1.0D)
						{
							var30 = var36;
						}
						else
						{
							var30 = var34 + (var36 - var34) * var38;
						}
						var30 -= var32;
						if (var46 > par6 -4)
						{
							double var40 = (var46 - (par6 -4)) / 3.0F;
							var30 = var30 * (1.0D - var40) + -10.0D * var40;
						}
						NoiseArray[var12] = var30;
						var12++;
					}
				}
			}
			return NoiseArray;
		}
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) 
	{

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
