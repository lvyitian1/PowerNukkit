package cn.nukkit.level.generator.populator.impl;

import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.chunk.IChunk;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.BedrockRandom;
import cn.nukkit.utils.Identifier;

import static cn.nukkit.block.BlockIds.*;

public class PopulatorGlowStone extends Populator {
    @Override
    public void populate(ChunkManager level, int chunkX, int chunkZ, BedrockRandom random, IChunk chunk) {
        int x = random.nextInt(chunkX << 4, (chunkX << 4) + 15);
        int z = random.nextInt(chunkZ << 4, (chunkZ << 4) + 15);
        int y = this.getHighestWorkableBlock(chunk, x & 0xF, z & 0xF);
        if (y != -1 && level.getBlockIdAt(x, y, z) != NETHERRACK) {
            int count = random.nextInt(40, 60);
            for (int i = 0; i < count; i++) {
                level.setBlockIdAt(x + (random.nextInt(7) - 3), y + (random.nextInt(9) - 4), z + (random.nextInt(7) - 3), GLOWSTONE);
            }
        }
    }

    private int getHighestWorkableBlock(IChunk chunk, int x, int z) {
        int y;
        //start scanning a bit lower down to allow space for placing on top
        for (y = 120; y >= 0; y--) {
            Identifier b = chunk.getBlockId(x, y, z);
            if (b == AIR) {
                break;
            }
        }
        return y == 0 ? -1 : y;
    }
}
