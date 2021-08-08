/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package de.gerrygames.viarewind.protocol.protocol1_7_6_10to1_8.types;

import com.viaversion.viaversion.api.minecraft.Environment;
import com.viaversion.viaversion.api.minecraft.chunks.Chunk;
import com.viaversion.viaversion.api.minecraft.chunks.ChunkSection;
import com.viaversion.viaversion.api.type.PartialType;
import com.viaversion.viaversion.protocols.protocol1_9_3to1_9_1_2.storage.ClientWorld;
import io.netty.buffer.ByteBuf;
import java.util.zip.Deflater;

public class Chunk1_7_10Type
extends PartialType<Chunk, ClientWorld> {
    public Chunk1_7_10Type(ClientWorld param) {
        super(param, Chunk.class);
    }

    @Override
    public Chunk read(ByteBuf byteBuf, ClientWorld clientWorld) throws Exception {
        throw new UnsupportedOperationException();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void write(ByteBuf output, ClientWorld clientWorld, Chunk chunk) throws Exception {
        int compressedSize;
        byte[] compressedData;
        boolean skyLight;
        int i;
        output.writeInt(chunk.getX());
        output.writeInt(chunk.getZ());
        output.writeBoolean(chunk.isFullChunk());
        output.writeShort(chunk.getBitmask());
        output.writeShort(0);
        ByteBuf dataToCompress = output.alloc().buffer();
        ByteBuf blockData = output.alloc().buffer();
        for (i = 0; i < chunk.getSections().length; ++i) {
            if ((chunk.getBitmask() & 1 << i) == 0) continue;
            ChunkSection section = chunk.getSections()[i];
            for (int y = 0; y < 16; ++y) {
                for (int z = 0; z < 16; ++z) {
                    int previousData = 0;
                    for (int x = 0; x < 16; ++x) {
                        int block = section.getFlatBlock(x, y, z);
                        dataToCompress.writeByte(block >> 4);
                        int data = block & 0xF;
                        if (x % 2 == 0) {
                            previousData = data;
                            continue;
                        }
                        blockData.writeByte(data << 4 | previousData);
                    }
                }
            }
        }
        dataToCompress.writeBytes(blockData);
        blockData.release();
        for (i = 0; i < chunk.getSections().length; ++i) {
            if ((chunk.getBitmask() & 1 << i) == 0) continue;
            chunk.getSections()[i].getLight().writeBlockLight(dataToCompress);
        }
        boolean bl = skyLight = clientWorld != null && clientWorld.getEnvironment() == Environment.NORMAL;
        if (skyLight) {
            for (int i2 = 0; i2 < chunk.getSections().length; ++i2) {
                if ((chunk.getBitmask() & 1 << i2) == 0) continue;
                chunk.getSections()[i2].getLight().writeSkyLight(dataToCompress);
            }
        }
        if (chunk.isFullChunk() && chunk.isBiomeData()) {
            for (int biome : chunk.getBiomeData()) {
                dataToCompress.writeByte((int)((byte)biome));
            }
        }
        dataToCompress.readerIndex(0);
        byte[] data = new byte[dataToCompress.readableBytes()];
        dataToCompress.readBytes(data);
        dataToCompress.release();
        Deflater deflater = new Deflater(4);
        try {
            deflater.setInput(data, 0, data.length);
            deflater.finish();
            compressedData = new byte[data.length];
            compressedSize = deflater.deflate(compressedData);
        }
        finally {
            deflater.end();
        }
        output.writeInt(compressedSize);
        output.writeBytes(compressedData, 0, compressedSize);
    }
}

