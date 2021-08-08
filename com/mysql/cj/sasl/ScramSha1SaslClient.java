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

public class ScramSha1SaslClient
extends ScramShaSaslClient {
    public static final String IANA_MECHANISM_NAME = "SCRAM-SHA-1";
    public static final String MECHANISM_NAME = "MYSQLCJ-SCRAM-SHA-1";
    private static final String SHA1_ALGORITHM = "SHA-1";
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String PBKCF2_HMAC_SHA1_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SHA1_HASH_LENGTH = 20;

    public ScramSha1SaslClient(String authorizationId, String authenticationId, String password) throws SaslException {
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
            MessageDigest sha1 = MessageDigest.getInstance(SHA1_ALGORITHM);
            return sha1.digest(str);
        }
        catch (NoSuchAlgorithmException e) {
            throw ExceptionFactory.createException("Failed computing authentication hashes", e);
        }
    }

    @Override
    byte[] hmac(byte[] key, byte[] str) {
        try {
            Mac hmacSha1 = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            hmacSha1.init(new SecretKeySpec(key, HMAC_SHA1_ALGORITHM));
            return hmacSha1.doFinal(str);
        }
        catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw ExceptionFactory.createException("Failed computing authentication hashes", e);
        }
    }

    @Override
    byte[] hi(String str, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(str.toCharArray(), salt, iterations, 160);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKCF2_HMAC_SHA1_ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw ExceptionFactory.createException(e.getMessage());
        }
    }
}

