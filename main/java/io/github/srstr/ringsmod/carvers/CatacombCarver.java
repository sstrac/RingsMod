package io.github.srstr.ringsmod.carvers;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.util.math.MathHelper;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CatacombCarver extends WorldCarver<ProbabilityConfig> {

    public CatacombCarver(Codec<ProbabilityConfig> p_i231921_1_) {
        super(p_i231921_1_, 256);
    }

    /**carve*/
    @Override
    public boolean func_225555_a_(IChunk chunk, Function<BlockPos, Biome> function, Random random, int i, int j, int k, int l, int m, BitSet bitSet, ProbabilityConfig p_225555_10_) {
        int branchFactor = 2;
        int n = (branchFactor * 2 - 1) * 16;
        int o = random.nextInt(random.nextInt(random.nextInt(this.getMaxCaveCount()) + 1) + 1);

        for(int p = 0; p < o; ++p) {
            double d = j * 16 + random.nextInt(16);
            double e = this.getCaveY(random);
            double f = k * 16 + random.nextInt(16);
            int q = 1;
            float t;
            if (random.nextInt(4) == 0) {
                t = 1.0F + random.nextFloat() * 6.0F;
                this.carveCave((Chunk) chunk, function, random.nextLong(), 32, l, m, d, e, f, t, 0.5D, bitSet);
                q += random.nextInt(4);
            }

            for(int r = 0; r < q; ++r) {
                float s = random.nextFloat() * 6.2831855F;
                t = (random.nextFloat() - 0.5F) / 4.0F;
                float u = this.getTunnelSystemWidth(random);
                int v = n - random.nextInt(n / 4);
                this.carveTunnels((Chunk) chunk, function, random.nextLong(), 32, l, m, d, e, f, u, s, t, 0, v, this.getTunnelSystemHeightWidthRatio(), bitSet);
            }
        }

        return true;
    }

    @Override
    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        return rand.nextFloat() <= config.probability;
    }
    /**isPositionExcluded*/
    @Override
    protected boolean func_222708_a(double scaledRelativeX, double scaledRelativeY, double scaledRelativeZ, int y){
        return scaledRelativeY <= -0.7D || scaledRelativeX * scaledRelativeX + scaledRelativeY * scaledRelativeY + scaledRelativeZ * scaledRelativeZ >= 1.0D;
    }

    protected int getMaxCaveCount() {
        return 30;
    }

    protected float getTunnelSystemWidth(Random random) {
        float f = random.nextFloat() * 2.0F + random.nextFloat();
        if (random.nextInt(10) == 0) {
            f *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
        }

        return f;
    }

    protected double getTunnelSystemHeightWidthRatio() {
        return 1.0D;
    }

    protected int getCaveY(Random random) {
        return random.nextInt(256);
    }

    protected void carveCave(Chunk chunk, Function<BlockPos, Biome> posToBiome, long seed, int seaLevel, int mainChunkX, int mainChunkZ, double x, double y, double z, float yaw, double yawPitchRatio, BitSet carvingMask) {
        double d = 1.5D + (double)(MathHelper.sin(1.5707964F) * yaw);
        double e = d * yawPitchRatio;
        this.func_227208_a_(chunk, posToBiome, seed, seaLevel, mainChunkX, mainChunkZ, x + 1.0D, y, z, d, e, carvingMask);
    }

    protected void carveTunnels(Chunk chunk, Function<BlockPos, Biome> postToBiome, long seed, int seaLevel, int mainChunkX, int mainChunkZ, double x, double y, double z, float width, float yaw, float pitch, int branchStartIndex, int branchCount, double yawPitchRatio, BitSet carvingMask) {
        Random random = new Random(seed);
        int i = random.nextInt(branchCount / 2) + branchCount / 4;
        boolean bl = random.nextInt(6) == 0;
        float f = 0.0F;
        float g = 0.0F;

        for(int j = branchStartIndex; j < branchCount; ++j) {
            double d = 1.5D + (double)(MathHelper.sin(3.1415927F * (float)j / (float)branchCount) * width);
            double e = d * yawPitchRatio;
            float h = MathHelper.cos(pitch);
            x += MathHelper.cos(yaw) * h;
            y += MathHelper.sin(pitch);
            z += MathHelper.sin(yaw) * h;
            pitch *= bl ? 0.92F : 0.7F;
            pitch += g * 0.1F;
            yaw += f * 0.1F;
            g *= 0.9F;
            f *= 0.75F;
            g += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && width > 1.0F) {
                this.carveTunnels(chunk, postToBiome, random.nextLong(), seaLevel, mainChunkX, mainChunkZ, x, y, z, random.nextFloat() * 0.5F + 0.5F, yaw - 1.5707964F, pitch / 3.0F, j, branchCount, 1.0D, carvingMask);
                this.carveTunnels(chunk, postToBiome, random.nextLong(), seaLevel, mainChunkX, mainChunkZ, x, y, z, random.nextFloat() * 0.5F + 0.5F, yaw + 1.5707964F, pitch / 3.0F, j, branchCount, 1.0D, carvingMask);
                return;
            }
        }

    }
}
