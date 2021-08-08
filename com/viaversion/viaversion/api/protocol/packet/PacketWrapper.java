/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelFuture
 *  org.checkerframework.checker.nullness.qual.Nullable
 */
package com.viaversion.viaversion.api.protocol.packet;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.Protocol;
import com.viaversion.viaversion.api.protocol.packet.Direction;
import com.viaversion.viaversion.api.protocol.packet.PacketType;
import com.viaversion.viaversion.api.protocol.packet.State;
import com.viaversion.viaversion.api.protocol.remapper.PacketHandler;
import com.viaversion.viaversion.api.type.Type;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface PacketWrapper {
    public static final int PASSTHROUGH_ID = 1000;

    public static PacketWrapper create(PacketType packetType, UserConnection connection) {
        return PacketWrapper.create(packetType.getId(), null, connection);
    }

    public static PacketWrapper create(PacketType packetType, @Nullable ByteBuf inputBuffer, UserConnection connection) {
        return PacketWrapper.create(packetType.getId(), inputBuffer, connection);
    }

    public static PacketWrapper create(int packetId, @Nullable ByteBuf inputBuffer, UserConnection connection) {
        return Via.getManager().getProtocolManager().createPacketWrapper(packetId, inputBuffer, connection);
    }

    public <T> T get(Type<T> var1, int var2) throws Exception;

    public boolean is(Type var1, int var2);

    public boolean isReadable(Type var1, int var2);

    public <T> void set(Type<T> var1, int var2, T var3) throws Exception;

    public <T> T read(Type<T> var1) throws Exception;

    public <T> void write(Type<T> var1, T var2);

    public <T> T passthrough(Type<T> var1) throws Exception;

    public void passthroughAll() throws Exception;

    public void writeToBuffer(ByteBuf var1) throws Exception;

    public void clearInputBuffer();

    public void clearPacket();

    default public void send(Class<? extends Protocol> protocol) throws Exception {
        this.send(protocol, true);
    }

    public void send(Class<? extends Protocol> var1, boolean var2) throws Exception;

    default public void scheduleSend(Class<? extends Protocol> protocol) throws Exception {
        this.scheduleSend(protocol, true);
    }

    public void scheduleSend(Class<? extends Protocol> var1, boolean var2) throws Exception;

    public ChannelFuture sendFuture(Class<? extends Protocol> var1) throws Exception;

    @Deprecated
    public void send() throws Exception;

    default public PacketWrapper create(PacketType packetType) {
        return this.create(packetType.getId());
    }

    default public PacketWrapper create(PacketType packetType, PacketHandler handler) throws Exception {
        return this.create(packetType.getId(), handler);
    }

    public PacketWrapper create(int var1);

    public PacketWrapper create(int var1, PacketHandler var2) throws Exception;

    public PacketWrapper apply(Direction var1, State var2, int var3, List<Protocol> var4, boolean var5) throws Exception;

    public PacketWrapper apply(Direction var1, State var2, int var3, List<Protocol> var4) throws Exception;

    public void cancel();

    public boolean isCancelled();

    public UserConnection user();

    public void resetReader();

    @Deprecated
    public void sendToServer() throws Exception;

    default public void sendToServer(Class<? extends Protocol> protocol) throws Exception {
        this.sendToServer(protocol, true);
    }

    public void sendToServer(Class<? extends Protocol> var1, boolean var2) throws Exception;

    default public void scheduleSendToServer(Class<? extends Protocol> protocol) throws Exception {
        this.scheduleSendToServer(protocol, true);
    }

    public void scheduleSendToServer(Class<? extends Protocol> var1, boolean var2) throws Exception;

    public int getId();

    default public void setId(PacketType packetType) {
        this.setId(packetType.getId());
    }

    public void setId(int var1);
}

