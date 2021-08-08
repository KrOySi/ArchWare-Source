/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.sasl;

import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.sasl.ScramShaSaslClient;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.sasl.SaslException;

public class ScramSha256SaslClient
extends ScramShaSaslClient {
    public static final String IANA_MECHANISM_NAME = "SCRAM-SHA-256";
    public static final String MECHANISM_NAME = "MYSQLCJ-SCRAM-SHA-256";
    private static final String SHA256_ALGORITHM = "SHA-256";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final String PBKCF2_HMAC_SHA256_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int SHA256_HASH_LENGTH = 32;

    public ScramSha256SaslClient(String authorizationId, String authenticationId, String password) throws SaslException {
        super(authorizationId, authenticationId, password);
    }

    @Override
    String getIanaMechanismName() {
        return IANA_MECHANISM_NAME;
    }

    @Override
    public String getMechanismName() {
        return MECHANISM_NAME;
    }

    @Override
    byte[] h(byte[] str) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance(SHA256_ALGORITHM);
            return sha256.digest(str);
        }
        catch (NoSuchAlgorithmException e) {
            throw ExceptionFactory.createException("Failed computing authentication hashes", e);
        }
    }

    @Override
    byte[] hmac(byte[] key, byte[] str) {
        try {
            Mac hmacSha256 = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            hmacSha256.init(new SecretKeySpec(key, HMAC_SHA256_ALGORITHM));
            return hmacSha256.doFinal(str);
        }
        catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw ExceptionFactory.createException("Failed computing authentication hashes", e);
        }
    }

    @Override
    byte[] hi(String str, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(str.toCharArray(), salt, iterations, 256);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKCF2_HMAC_SHA256_ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw ExceptionFactory.createException(e.getMessage());
        }
    }
}

