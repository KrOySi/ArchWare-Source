/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.sasl;

import com.mysql.cj.util.SaslPrep;
import com.mysql.cj.util.StringUtils;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

public abstract class ScramShaSaslClient
implements SaslClient {
    protected static final int MINIMUM_ITERATIONS = 4096;
    protected static final String GS2_CBIND_FLAG = "n";
    protected static final byte[] CLIENT_KEY = "Client Key".getBytes();
    protected static final byte[] SERVER_KEY = "Server Key".getBytes();
    protected String authorizationId;
    protected String authenticationId;
    protected String password;
    protected ScramExchangeStage scramStage = ScramExchangeStage.CLIENT_FIRST;
    protected String cNonce;
    protected String gs2Header;
    protected String clientFirstMessageBare;
    protected byte[] serverSignature;

    public ScramShaSaslClient(String authorizationId, String authenticationId, String password) throws SaslException {
        this.authorizationId = StringUtils.isNullOrEmpty(authorizationId) ? "" : authorizationId;
        String string = this.authenticationId = StringUtils.isNullOrEmpty(authenticationId) ? this.authorizationId : authenticationId;
        if (StringUtils.isNullOrEmpty(this.authenticationId)) {
            throw new SaslException("The authenticationId cannot be null or empty.");
        }
        this.password = StringUtils.isNullOrEmpty(password) ? "" : password;
        this.scramStage = ScramExchangeStage.CLIENT_FIRST;
    }

    abstract String getIanaMechanismName();

    @Override
    public boolean hasInitialResponse() {
        return true;
    }

    @Override
    public byte[] evaluateChallenge(byte[] challenge) throws SaslException {
        try {
            switch (this.scramStage) {
                case CLIENT_FIRST: {
                    this.gs2Header = "n," + (StringUtils.isNullOrEmpty(this.authorizationId) ? "" : "a=" + this.prepUserName(this.authorizationId)) + ",";
                    this.cNonce = this.generateRandomPrintableAsciiString(32);
                    this.clientFirstMessageBare = "n=" + this.prepUserName(this.authenticationId) + ",r=" + this.cNonce;
                    String clientFirstMessage = this.gs2Header + this.clientFirstMessageBare;
                    byte[] arrby = StringUtils.getBytes(clientFirstMessage, "UTF-8");
                    return arrby;
                }
                case SERVER_FIRST_CLIENT_FINAL: {
                    String serverFirstMessage = StringUtils.toString(challenge, "UTF-8");
                    Map<String, String> serverFirstAttributes = this.parseChallenge(serverFirstMessage);
                    if (!(serverFirstAttributes.containsKey("r") && serverFirstAttributes.containsKey("s") && serverFirstAttributes.containsKey("i"))) {
                        throw new SaslException("Missing required SCRAM attribute from server first message.");
                    }
                    String sNonce = serverFirstAttributes.get("r");
                    if (!sNonce.startsWith(this.cNonce)) {
                        throw new SaslException("Invalid server nonce for " + this.getIanaMechanismName() + " authentication.");
                    }
                    byte[] salt = Base64.getDecoder().decode(serverFirstAttributes.get("s"));
                    int iterations = Integer.parseInt(serverFirstAttributes.get("i"));
                    if (iterations < 4096) {
                        throw new SaslException("Announced " + this.getIanaMechanismName() + " iteration count is too low.");
                    }
                    String clientFinalMessageWithoutProof = "c=" + Base64.getEncoder().encodeToString(StringUtils.getBytes(this.gs2Header, "UTF-8")) + ",r=" + sNonce;
                    byte[] saltedPassword = this.hi(SaslPrep.prepare(this.password, SaslPrep.StringType.STORED), salt, iterations);
                    byte[] clientKey = this.hmac(saltedPassword, CLIENT_KEY);
                    byte[] storedKey = this.h(clientKey);
                    String authMessage = this.clientFirstMessageBare + "," + serverFirstMessage + "," + clientFinalMessageWithoutProof;
                    byte[] clientSignature = this.hmac(storedKey, StringUtils.getBytes(authMessage, "UTF-8"));
                    byte[] clientProof = (byte[])clientKey.clone();
                    this.xorInPlace(clientProof, clientSignature);
                    String clientFinalMessage = clientFinalMessageWithoutProof + ",p=" + Base64.getEncoder().encodeToString(clientProof);
                    byte[] serverKey = this.hmac(saltedPassword, SERVER_KEY);
                    this.serverSignature = this.hmac(serverKey, StringUtils.getBytes(authMessage, "UTF-8"));
                    byte[] arrby = StringUtils.getBytes(clientFinalMessage, "UTF-8");
                    return arrby;
                }
                case SERVER_FINAL: {
                    String serverFinalMessage = StringUtils.toString(challenge, "UTF-8");
                    Map<String, String> serverFinalAttributes = this.parseChallenge(serverFinalMessage);
                    if (serverFinalAttributes.containsKey("e")) {
                        throw new SaslException("Authentication failed due to server error '" + serverFinalAttributes.get("e") + "'.");
                    }
                    if (!serverFinalAttributes.containsKey("v")) {
                        throw new SaslException("Missing required SCRAM attribute from server final message.");
                    }
                    byte[] verifier = Base64.getDecoder().decode(serverFinalAttributes.get("v"));
                    if (MessageDigest.isEqual(this.serverSignature, verifier)) break;
                    throw new SaslException(this.getIanaMechanismName() + " server signature could not be verified.");
                }
                default: {
                    throw new SaslException("Unexpected SCRAM authentication message.");
                }
            }
            byte[] clientFirstMessage = null;
            return clientFirstMessage;
        }
        catch (Throwable e) {
            this.scramStage = ScramExchangeStage.TERMINATED;
            throw e;
        }
        finally {
            this.scramStage = this.scramStage.getNext();
        }
    }

    @Override
    public boolean isComplete() {
        return this.scramStage == ScramExchangeStage.TERMINATED;
    }

    @Override
    public byte[] unwrap(byte[] incoming, int offset, int len) throws SaslException {
        throw new IllegalStateException("Integrity and/or privacy has not been negotiated.");
    }

    @Override
    public byte[] wrap(byte[] outgoing, int offset, int len) throws SaslException {
        throw new IllegalStateException("Integrity and/or privacy has not been negotiated.");
    }

    @Override
    public Object getNegotiatedProperty(String propName) {
        return null;
    }

    @Override
    public void dispose() throws SaslException {
    }

    private String prepUserName(String userName) {
        return SaslPrep.prepare(userName, SaslPrep.StringType.QUERY).replace("=", "=2D").replace(",", "=2C");
    }

    private Map<String, String> parseChallenge(String challenge) {
        HashMap<String, String> attributesMap = new HashMap<String, String>();
        for (String attribute : challenge.split(",")) {
            String[] keyValue = attribute.split("=", 2);
            attributesMap.put(keyValue[0], keyValue[1]);
        }
        return attributesMap;
    }

    private String generateRandomPrintableAsciiString(int length) {
        int first = 33;
        int last = 126;
        int excl = 44;
        int bound = 93;
        SecureRandom random = new SecureRandom();
        char[] result = new char[length];
        int i = 0;
        while (i < length) {
            int randomValue = random.nextInt(93) + 33;
            if (randomValue == 44) continue;
            result[i++] = (char)randomValue;
        }
        return new String(result);
    }

    abstract byte[] h(byte[] var1);

    abstract byte[] hmac(byte[] var1, byte[] var2);

    abstract byte[] hi(String var1, byte[] var2, int var3);

    byte[] xorInPlace(byte[] inOut, byte[] other) {
        for (int i = 0; i < inOut.length; ++i) {
            int n = i;
            inOut[n] = (byte)(inOut[n] ^ other[i]);
        }
        return inOut;
    }

    protected static enum ScramExchangeStage {
        TERMINATED(null),
        SERVER_FINAL(TERMINATED),
        SERVER_FIRST_CLIENT_FINAL(SERVER_FINAL),
        CLIENT_FIRST(SERVER_FIRST_CLIENT_FINAL);

        private ScramExchangeStage next;

        private ScramExchangeStage(ScramExchangeStage next) {
            this.next = next;
        }

        public ScramExchangeStage getNext() {
            return this.next == null ? this : this.next;
        }
    }
}

