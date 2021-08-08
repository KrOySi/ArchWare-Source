/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.TypeAdapter
 *  com.google.gson.TypeAdapterFactory
 *  com.google.gson.reflect.TypeToken
 *  com.google.gson.stream.JsonReader
 *  com.google.gson.stream.JsonToken
 *  com.google.gson.stream.JsonWriter
 *  javax.annotation.Nullable
 */
package net.minecraft.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Nullable;

public class EnumTypeAdapterFactory
implements TypeAdapterFactory {
    @Nullable
    public <T> TypeAdapter<T> create(Gson p_create_1_, TypeToken<T> p_create_2_) {
        Class oclass = p_create_2_.getRawType();
        if (!oclass.isEnum()) {
            return null;
        }
        final HashMap map = Maps.newHashMap();
        for (Object t : oclass.getEnumConstants()) {
            map.put(this.getName(t), t);
        }
        return new TypeAdapter<T>(){

            public void write(JsonWriter p_write_1_, T p_write_2_) throws IOException {
                if (p_write_2_ == null) {
                    p_write_1_.nullValue();
                } else {
                    p_write_1_.value(EnumTypeAdapterFactory.this.getName(p_write_2_));
                }
            }

            @Nullable
            public T read(JsonReader p_read_1_) throws IOException {
                if (p_read_1_.peek() == JsonToken.NULL) {
                    p_read_1_.nextNull();
                    return null;
                }
                return map.get(p_read_1_.nextString());
            }
        };
    }

    private String getName(Object objectIn) {
        return objectIn instanceof Enum ? ((Enum)objectIn).name().toLowerCase(Locale.ROOT) : objectIn.toString().toLowerCase(Locale.ROOT);
    }
}

