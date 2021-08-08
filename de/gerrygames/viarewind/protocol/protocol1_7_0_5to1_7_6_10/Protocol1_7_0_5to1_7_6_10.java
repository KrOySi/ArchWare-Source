/*
 * Decompiled with CFR 0.150.
 */
package de.gerrygames.viarewind.protocol.protocol1_7_0_5to1_7_6_10;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.AbstractSimpleProtocol;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.protocol.packet.State;
import com.viaversion.viaversion.api.protocol.remapper.PacketRemapper;
import com.viaversion.viaversion.api.protocol.remapper.ValueTransformer;
import com.viaversion.viaversion.api.type.Type;
import de.gerrygames.viarewind.protocol.protocol1_7_6_10to1_8.types.Types1_7_6_10;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Protocol1_7_0_5to1_7_6_10
extends AbstractSimpleProtocol {
    public static final ValueTransformer<String, String> REMOVE_DASHES = new ValueTransformer<String, String>(Type.STRING){

        @Override
        public String transform(PacketWrapper packetWrapper, String s) {
            return s.replace("-", "");
        }
    };

    @Override
    protected void registerPackets() {
        this.registerClientbound(State.LOGIN, 2, 2, new PacketRemapper(){

            @Override
            public void registerMap() {
                this.map(Type.STRING, REMOVE_DASHES);
                this.map(Type.STRING);
            }
        });
        this.registerClientbound(State.PLAY, 12, 12, new PacketRemapper(){

            @Override
            public void registerMap() {
                this.map(Type.VAR_INT);
                this.map(Type.STRING, REMOVE_DASHES);
                this.map(Type.STRING);
                this.handler(packetWrapper -> {
                    int size = packetWrapper.read(Type.VAR_INT);
                    for (int i = 0; i < size * 3; ++i) {
                        packetWrapper.read(Type.STRING);
                    }
                });
                this.map(Type.INT);
                this.map(Type.INT);
                this.map(Type.INT);
                this.map(Type.BYTE);
                this.map(Type.BYTE);
                this.map(Type.SHORT);
                this.map(Types1_7_6_10.METADATA_LIST);
            }
        });
        this.registerClientbound(State.PLAY, 62, 62, new PacketRemapper(){

            @Override
            public void registerMap() {
                this.map(Type.STRING);
                this.map(Type.BYTE);
                this.handler(packetWrapper -> {
                    byte mode = packetWrapper.get(Type.BYTE, 0);
                    if (mode == 0 || mode == 2) {
                        packetWrapper.passthrough(Type.STRING);
                        packetWrapper.passthrough(Type.STRING);
                        packetWrapper.passthrough(Type.STRING);
                        packetWrapper.passthrough(Type.BYTE);
                    }
                    if (mode == 0 || mode == 3 || mode == 4) {
                        List<Object> entryList = new ArrayList<String>();
                        int size = packetWrapper.read(Type.SHORT).shortValue();
                        for (int i = 0; i < size; ++i) {
                            entryList.add(packetWrapper.read(Type.STRING));
                        }
                        entryList = entryList.stream().map((? super T it) -> it.length() > 16 ? it.substring(0, 16) : it).distinct().collect(Collectors.toList());
                        packetWrapper.write(Type.SHORT, (short)entryList.size());
                        for (String string : entryList) {
                            packetWrapper.write(Type.STRING, string);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void init(UserConnection userConnection) {
    }
}

