/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol.x;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.x.CompressionMode;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.InflaterInputStream;

public class CompressionAlgorithm {
    private static final Map<String, String> ALIASES = new HashMap<String, String>();
    private String algorithmIdentifier;
    private CompressionMode compressionMode;
    private String inputStreamClassFqn;
    private Class<?> inputStreamClass = null;
    private String outputStreamClassFqn;
    private Class<?> outputStreamClass = null;

    public static Map<String, CompressionAlgorithm> getDefaultInstances() {
        HashMap<String, CompressionAlgorithm> defaultInstances = new HashMap<String, CompressionAlgorithm>();
        defaultInstances.put("deflate_stream", new CompressionAlgorithm("deflate_stream", InflaterInputStream.class.getName(), SyncFlushDeflaterOutputStream.class.getName()));
        return defaultInstances;
    }

    public static String getNormalizedAlgorithmName(String name) {
        return ALIASES.getOrDefault(name, name);
    }

    public CompressionAlgorithm(String name, String inputStreamClassFqn, String outputStreamClassFqn) {
        this.algorithmIdentifier = CompressionAlgorithm.getNormalizedAlgorithmName(name);
        String[] nameMode = this.algorithmIdentifier.split("_");
        if (nameMode.length != 2) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Protocol.Compression.4", new Object[]{name}));
        }
        try {
            CompressionMode mode;
            this.compressionMode = mode = CompressionMode.valueOf(nameMode[1].toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Protocol.Compression.5", new Object[]{nameMode[1]}));
        }
        this.inputStreamClassFqn = inputStreamClassFqn;
        this.outputStreamClassFqn = outputStreamClassFqn;
    }

    public String getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public CompressionMode getCompressionMode() {
        return this.compressionMode;
    }

    public Class<?> getInputStreamClass() {
        if (this.inputStreamClass == null) {
            try {
                this.inputStreamClass = Class.forName(this.inputStreamClassFqn);
            }
            catch (ClassNotFoundException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Protocol.Compression.3", new Object[]{this.inputStreamClassFqn}), e);
            }
        }
        return this.inputStreamClass;
    }

    public Class<?> getOutputStreamClass() {
        if (this.outputStreamClass == null) {
            try {
                this.outputStreamClass = Class.forName(this.outputStreamClassFqn);
            }
            catch (ClassNotFoundException e) {
                throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("Protocol.Compression.3", new Object[]{this.outputStreamClassFqn}), e);
            }
        }
        return this.outputStreamClass;
    }

    static {
        ALIASES.put("deflate", "deflate_stream");
        ALIASES.put("lz4", "lz4_message");
        ALIASES.put("zstd", "zstd_stream");
    }
}

