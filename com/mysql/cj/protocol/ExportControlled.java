/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.conf.PropertyDefinitions;
import com.mysql.cj.conf.PropertyKey;
import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.exceptions.FeatureNotAvailableException;
import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.exceptions.SSLParamsException;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.protocol.SocketConnection;
import com.mysql.cj.util.Base64Decoder;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class ExportControlled {
    private static final String TLSv1 = "TLSv1";
    private static final String TLSv1_1 = "TLSv1.1";
    private static final String TLSv1_2 = "TLSv1.2";
    private static final String TLSv1_3 = "TLSv1.3";
    private static final String[] TLS_PROTOCOLS = new String[]{"TLSv1.3", "TLSv1.2", "TLSv1.1", "TLSv1"};
    private static final String TLS_SETTINGS_RESOURCE = "/com/mysql/cj/TlsSettings.properties";
    private static final List<String> ALLOWED_CIPHERS = new ArrayList<String>();
    private static final List<String> RESTRICTED_CIPHER_SUBSTR = new ArrayList<String>();

    private ExportControlled() {
    }

    public static boolean enabled() {
        return true;
    }

    private static String[] getAllowedCiphers(PropertySet pset, List<String> socketCipherSuites) {
        String enabledSSLCipherSuites = pset.getStringProperty(PropertyKey.enabledSSLCipherSuites).getValue();
        Stream filterStream = StringUtils.isNullOrEmpty(enabledSSLCipherSuites) ? socketCipherSuites.stream() : Arrays.stream(enabledSSLCipherSuites.split("\\s*,\\s*")).filter(socketCipherSuites::contains);
        List<String> allowedCiphers = filterStream.filter(ALLOWED_CIPHERS::contains).filter(c -> !RESTRICTED_CIPHER_SUBSTR.stream().filter(r -> c.contains((CharSequence)r)).findFirst().isPresent()).collect(Collectors.toList());
        return allowedCiphers.toArray(new String[0]);
    }

    private static String[] getAllowedProtocols(PropertySet pset, ServerVersion serverVersion, String[] socketProtocols) {
        String[] tryProtocols = null;
        String enabledTLSProtocols = pset.getStringProperty(PropertyKey.enabledTLSProtocols).getValue();
        tryProtocols = enabledTLSProtocols != null && enabledTLSProtocols.length() > 0 ? enabledTLSProtocols.split("\\s*,\\s*") : (serverVersion == null ? TLS_PROTOCOLS : (serverVersion.meetsMinimum(new ServerVersion(5, 7, 28)) || serverVersion.meetsMinimum(new ServerVersion(5, 6, 46)) && !serverVersion.meetsMinimum(new ServerVersion(5, 7, 0)) || serverVersion.meetsMinimum(new ServerVersion(5, 6, 0)) && Util.isEnterpriseEdition(serverVersion.toString()) ? TLS_PROTOCOLS : new String[]{TLSv1_1, TLSv1}));
        ArrayList<String> configuredProtocols = new ArrayList<String>(Arrays.asList(tryProtocols));
        List<String> jvmSupportedProtocols = Arrays.asList(socketProtocols);
        ArrayList<String> allowedProtocols = new ArrayList<String>();
        for (String protocol : TLS_PROTOCOLS) {
            if (!jvmSupportedProtocols.contains(protocol) || !configuredProtocols.contains(protocol)) continue;
            allowedProtocols.add(protocol);
        }
        return allowedProtocols.toArray(new String[0]);
    }

    public static void checkValidProtocols(List<String> protocols) {
        List<String> validProtocols = Arrays.asList(TLS_PROTOCOLS);
        for (String protocol : protocols) {
            if (validProtocols.contains(protocol)) continue;
            throw ExceptionFactory.createException(WrongArgumentException.class, "'" + protocol + "' not recognized as a valid TLS protocol version (should be one of " + Arrays.stream(TLS_PROTOCOLS).collect(Collectors.joining(", ")) + ").");
        }
    }

    private static KeyStoreConf getTrustStoreConf(PropertySet propertySet, boolean required) {
        String trustStoreUrl = propertySet.getStringProperty(PropertyKey.trustCertificateKeyStoreUrl).getValue();
        String trustStorePassword = propertySet.getStringProperty(PropertyKey.trustCertificateKeyStorePassword).getValue();
        String trustStoreType = propertySet.getStringProperty(PropertyKey.trustCertificateKeyStoreType).getValue();
        boolean fallbackToSystemTrustStore = propertySet.getBooleanProperty(PropertyKey.fallbackToSystemTrustStore).getValue();
        if (fallbackToSystemTrustStore && StringUtils.isNullOrEmpty(trustStoreUrl)) {
            trustStoreUrl = System.getProperty("javax.net.ssl.trustStore");
            trustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
            trustStoreType = System.getProperty("javax.net.ssl.trustStoreType");
            if (StringUtils.isNullOrEmpty(trustStoreType)) {
                trustStoreType = propertySet.getStringProperty(PropertyKey.trustCertificateKeyStoreType).getInitialValue();
            }
            if (!StringUtils.isNullOrEmpty(trustStoreUrl)) {
                try {
                    new URL(trustStoreUrl);
                }
                catch (MalformedURLException e) {
                    trustStoreUrl = "file:" + trustStoreUrl;
                }
            }
        }
        if (required && StringUtils.isNullOrEmpty(trustStoreUrl)) {
            throw new CJCommunicationsException("No truststore provided to verify the Server certificate.");
        }
        return new KeyStoreConf(trustStoreUrl, trustStorePassword, trustStoreType);
    }

    private static KeyStoreConf getKeyStoreConf(PropertySet propertySet) {
        String keyStoreUrl = propertySet.getStringProperty(PropertyKey.clientCertificateKeyStoreUrl).getValue();
        String keyStorePassword = propertySet.getStringProperty(PropertyKey.clientCertificateKeyStorePassword).getValue();
        String keyStoreType = propertySet.getStringProperty(PropertyKey.clientCertificateKeyStoreType).getValue();
        boolean fallbackToSystemKeyStore = propertySet.getBooleanProperty(PropertyKey.fallbackToSystemKeyStore).getValue();
        if (fallbackToSystemKeyStore && StringUtils.isNullOrEmpty(keyStoreUrl)) {
            keyStoreUrl = System.getProperty("javax.net.ssl.keyStore");
            keyStorePassword = System.getProperty("javax.net.ssl.keyStorePassword");
            keyStoreType = System.getProperty("javax.net.ssl.keyStoreType");
            if (StringUtils.isNullOrEmpty(keyStoreType)) {
                keyStoreType = propertySet.getStringProperty(PropertyKey.clientCertificateKeyStoreType).getInitialValue();
            }
            if (!StringUtils.isNullOrEmpty(keyStoreUrl)) {
                try {
                    new URL(keyStoreUrl);
                }
                catch (MalformedURLException e) {
                    keyStoreUrl = "file:" + keyStoreUrl;
                }
            }
        }
        return new KeyStoreConf(keyStoreUrl, keyStorePassword, keyStoreType);
    }

    public static Socket performTlsHandshake(Socket rawSocket, SocketConnection socketConnection, ServerVersion serverVersion) throws IOException, SSLParamsException, FeatureNotAvailableException {
        PropertySet pset = socketConnection.getPropertySet();
        PropertyDefinitions.SslMode sslMode = (PropertyDefinitions.SslMode)((Object)pset.getEnumProperty(PropertyKey.sslMode).getValue());
        boolean verifyServerCert = sslMode == PropertyDefinitions.SslMode.VERIFY_CA || sslMode == PropertyDefinitions.SslMode.VERIFY_IDENTITY;
        boolean fallbackToSystemTrustStore = pset.getBooleanProperty(PropertyKey.fallbackToSystemTrustStore).getValue();
        KeyStoreConf trustStore = !verifyServerCert ? new KeyStoreConf() : ExportControlled.getTrustStoreConf(pset, serverVersion == null && verifyServerCert && !fallbackToSystemTrustStore);
        KeyStoreConf keyStore = ExportControlled.getKeyStoreConf(pset);
        SSLSocketFactory socketFactory = ExportControlled.getSSLContext(keyStore, trustStore, fallbackToSystemTrustStore, verifyServerCert, sslMode == PropertyDefinitions.SslMode.VERIFY_IDENTITY ? socketConnection.getHost() : null, socketConnection.getExceptionInterceptor()).getSocketFactory();
        SSLSocket sslSocket = (SSLSocket)socketFactory.createSocket(rawSocket, socketConnection.getHost(), socketConnection.getPort(), true);
        String[] allowedProtocols = ExportControlled.getAllowedProtocols(pset, serverVersion, sslSocket.getSupportedProtocols());
        sslSocket.setEnabledProtocols(allowedProtocols);
        String[] allowedCiphers = ExportControlled.getAllowedCiphers(pset, Arrays.asList(sslSocket.getEnabledCipherSuites()));
        if (allowedCiphers != null) {
            sslSocket.setEnabledCipherSuites(allowedCiphers);
        }
        sslSocket.startHandshake();
        return sslSocket;
    }

    public static SSLContext getSSLContext(KeyStoreConf clientCertificateKeyStore, KeyStoreConf trustCertificateKeyStore, boolean fallbackToDefaultTrustStore, boolean verifyServerCert, String hostName, ExceptionInterceptor exceptionInterceptor) throws SSLParamsException {
        String clientCertificateKeyStoreUrl = clientCertificateKeyStore.keyStoreUrl;
        String clientCertificateKeyStoreType = clientCertificateKeyStore.keyStoreType;
        String clientCertificateKeyStorePassword = clientCertificateKeyStore.keyStorePassword;
        String trustCertificateKeyStoreUrl = trustCertificateKeyStore.keyStoreUrl;
        String trustCertificateKeyStoreType = trustCertificateKeyStore.keyStoreType;
        String trustCertificateKeyStorePassword = trustCertificateKeyStore.keyStorePassword;
        TrustManagerFactory tmf = null;
        KeyManagerFactory kmf = null;
        KeyManager[] kms = null;
        ArrayList<TrustManager> tms = new ArrayList<TrustManager>();
        try {
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        }
        catch (NoSuchAlgorithmException nsae) {
            throw ExceptionFactory.createException(SSLParamsException.class, "Default algorithm definitions for TrustManager and/or KeyManager are invalid.  Check java security properties file.", nsae, exceptionInterceptor);
        }
        if (!StringUtils.isNullOrEmpty(clientCertificateKeyStoreUrl)) {
            InputStream ksIS = null;
            try {
                if (!StringUtils.isNullOrEmpty(clientCertificateKeyStoreType)) {
                    KeyStore clientKeyStore = KeyStore.getInstance(clientCertificateKeyStoreType);
                    URL ksURL = new URL(clientCertificateKeyStoreUrl);
                    char[] password = clientCertificateKeyStorePassword == null ? new char[0] : clientCertificateKeyStorePassword.toCharArray();
                    ksIS = ksURL.openStream();
                    clientKeyStore.load(ksIS, password);
                    kmf.init(clientKeyStore, password);
                    kms = kmf.getKeyManagers();
                }
            }
            catch (UnrecoverableKeyException uke) {
                throw ExceptionFactory.createException(SSLParamsException.class, "Could not recover keys from client keystore.  Check password?", uke, exceptionInterceptor);
            }
            catch (NoSuchAlgorithmException nsae) {
                throw ExceptionFactory.createException(SSLParamsException.class, "Unsupported keystore algorithm [" + nsae.getMessage() + "]", nsae, exceptionInterceptor);
            }
            catch (KeyStoreException kse) {
                throw ExceptionFactory.createException(SSLParamsException.class, "Could not create KeyStore instance [" + kse.getMessage() + "]", kse, exceptionInterceptor);
            }
            catch (CertificateException nsae) {
                throw ExceptionFactory.createException(SSLParamsException.class, "Could not load client" + clientCertificateKeyStoreType + " keystore from " + clientCertificateKeyStoreUrl, nsae, exceptionInterceptor);
            }
            catch (MalformedURLException mue) {
                throw ExceptionFactory.createException(SSLParamsException.class, clientCertificateKeyStoreUrl + " does not appear to be a valid URL.", mue, exceptionInterceptor);
            }
            catch (IOException ioe) {
                throw ExceptionFactory.createException(SSLParamsException.class, "Cannot open " + clientCertificateKeyStoreUrl + " [" + ioe.getMessage() + "]", ioe, exceptionInterceptor);
            }
            finally {
                if (ksIS != null) {
                    try {
                        ksIS.close();
                    }
                    catch (IOException iOException) {}
                }
            }
        }
        InputStream trustStoreIS = null;
        try {
            String trustStoreType = "";
            char[] trustStorePassword = null;
            KeyStore trustKeyStore = null;
            if (!StringUtils.isNullOrEmpty(trustCertificateKeyStoreUrl) && !StringUtils.isNullOrEmpty(trustCertificateKeyStoreType)) {
                trustStoreType = trustCertificateKeyStoreType;
                trustStorePassword = trustCertificateKeyStorePassword == null ? new char[0] : trustCertificateKeyStorePassword.toCharArray();
                trustStoreIS = new URL(trustCertificateKeyStoreUrl).openStream();
                trustKeyStore = KeyStore.getInstance(trustStoreType);
                trustKeyStore.load(trustStoreIS, trustStorePassword);
            }
            if (trustKeyStore != null || verifyServerCert && fallbackToDefaultTrustStore) {
                TrustManager[] origTms;
                tmf.init(trustKeyStore);
                for (TrustManager tm : origTms = tmf.getTrustManagers()) {
                    tms.add(tm instanceof X509TrustManager ? new X509TrustManagerWrapper((X509TrustManager)tm, verifyServerCert, hostName) : tm);
                }
            }
        }
        catch (MalformedURLException e) {
            throw ExceptionFactory.createException(SSLParamsException.class, trustCertificateKeyStoreUrl + " does not appear to be a valid URL.", e, exceptionInterceptor);
        }
        catch (NoSuchAlgorithmException e) {
            throw ExceptionFactory.createException(SSLParamsException.class, "Unsupported keystore algorithm [" + e.getMessage() + "]", e, exceptionInterceptor);
        }
        catch (KeyStoreException e) {
            throw ExceptionFactory.createException(SSLParamsException.class, "Could not create KeyStore instance [" + e.getMessage() + "]", e, exceptionInterceptor);
        }
        catch (CertificateException e) {
            throw ExceptionFactory.createException(SSLParamsException.class, "Could not load trust" + trustCertificateKeyStoreType + " keystore from " + trustCertificateKeyStoreUrl, e, exceptionInterceptor);
        }
        catch (IOException e) {
            throw ExceptionFactory.createException(SSLParamsException.class, "Cannot open " + trustCertificateKeyStoreUrl + " [" + e.getMessage() + "]", e, exceptionInterceptor);
        }
        finally {
            if (trustStoreIS != null) {
                try {
                    trustStoreIS.close();
                }
                catch (IOException iOException) {}
            }
        }
        if (tms.size() == 0) {
            tms.add(new X509TrustManagerWrapper(verifyServerCert, hostName));
        }
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kms, tms.toArray(new TrustManager[tms.size()]), null);
            return sslContext;
        }
        catch (NoSuchAlgorithmException nsae) {
            throw new SSLParamsException("TLS is not a valid SSL protocol.", nsae);
        }
        catch (KeyManagementException kme) {
            throw new SSLParamsException("KeyManagementException: " + kme.getMessage(), kme);
        }
    }

    public static boolean isSSLEstablished(Socket socket) {
        return socket == null ? false : SSLSocket.class.isAssignableFrom(socket.getClass());
    }

    public static RSAPublicKey decodeRSAPublicKey(String key) throws RSAException {
        if (key == null) {
            throw ExceptionFactory.createException(RSAException.class, "Key parameter is null");
        }
        int offset = key.indexOf("\n") + 1;
        int len = key.indexOf("-----END PUBLIC KEY-----") - offset;
        byte[] certificateData = Base64Decoder.decode(key.getBytes(), offset, len);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(certificateData);
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey)kf.generatePublic(spec);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw ExceptionFactory.createException(RSAException.class, "Unable to decode public key", e);
        }
    }

    public static byte[] encryptWithRSAPublicKey(byte[] source, RSAPublicKey key, String transformation) throws RSAException {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(1, key);
            return cipher.doFinal(source);
        }
        catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw ExceptionFactory.createException(RSAException.class, e.getMessage(), e);
        }
    }

    public static byte[] encryptWithRSAPublicKey(byte[] source, RSAPublicKey key) throws RSAException {
        return ExportControlled.encryptWithRSAPublicKey(source, key, "RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
    }

    static {
        try {
            Properties tlsSettings = new Properties();
            tlsSettings.load(ExportControlled.class.getResourceAsStream(TLS_SETTINGS_RESOURCE));
            Arrays.stream(tlsSettings.getProperty("TLSCiphers.Mandatory").split("\\s*,\\s*")).forEach(s -> ALLOWED_CIPHERS.add(s.trim()));
            Arrays.stream(tlsSettings.getProperty("TLSCiphers.Approved").split("\\s*,\\s*")).forEach(s -> ALLOWED_CIPHERS.add(s.trim()));
            Arrays.stream(tlsSettings.getProperty("TLSCiphers.Deprecated").split("\\s*,\\s*")).forEach(s -> ALLOWED_CIPHERS.add(s.trim()));
            Arrays.stream(tlsSettings.getProperty("TLSCiphers.Unacceptable.Mask").split("\\s*,\\s*")).forEach(s -> RESTRICTED_CIPHER_SUBSTR.add(s.trim()));
        }
        catch (IOException e) {
            throw ExceptionFactory.createException("Unable to load TlsSettings.properties");
        }
    }

    public static class X509TrustManagerWrapper
    implements X509TrustManager {
        private X509TrustManager origTm = null;
        private boolean verifyServerCert = false;
        private String hostName = null;
        private CertificateFactory certFactory = null;
        private PKIXParameters validatorParams = null;
        private CertPathValidator validator = null;

        public X509TrustManagerWrapper(X509TrustManager tm, boolean verifyServerCertificate, String hostName) throws CertificateException {
            this.origTm = tm;
            this.verifyServerCert = verifyServerCertificate;
            this.hostName = hostName;
            if (verifyServerCertificate) {
                try {
                    Set<TrustAnchor> anch = Arrays.stream(tm.getAcceptedIssuers()).map(c -> new TrustAnchor((X509Certificate)c, null)).collect(Collectors.toSet());
                    this.validatorParams = new PKIXParameters(anch);
                    this.validatorParams.setRevocationEnabled(false);
                    this.validator = CertPathValidator.getInstance("PKIX");
                    this.certFactory = CertificateFactory.getInstance("X.509");
                }
                catch (Exception e) {
                    throw new CertificateException(e);
                }
            }
        }

        public X509TrustManagerWrapper(boolean verifyServerCertificate, String hostName) {
            this.verifyServerCert = verifyServerCertificate;
            this.hostName = hostName;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return this.origTm != null ? this.origTm.getAcceptedIssuers() : new X509Certificate[0];
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            for (int i = 0; i < chain.length; ++i) {
                chain[i].checkValidity();
            }
            if (this.validatorParams != null) {
                X509CertSelector certSelect = new X509CertSelector();
                certSelect.setSerialNumber(chain[0].getSerialNumber());
                try {
                    CertPath certPath = this.certFactory.generateCertPath(Arrays.asList(chain));
                    CertPathValidatorResult result = this.validator.validate(certPath, this.validatorParams);
                    ((PKIXCertPathValidatorResult)result).getTrustAnchor().getTrustedCert().checkValidity();
                }
                catch (InvalidAlgorithmParameterException e) {
                    throw new CertificateException(e);
                }
                catch (CertPathValidatorException e) {
                    throw new CertificateException(e);
                }
            }
            if (this.verifyServerCert) {
                if (this.origTm == null) {
                    throw new CertificateException("Can't verify server certificate because no trust manager is found.");
                }
                this.origTm.checkServerTrusted(chain, authType);
                if (this.hostName != null) {
                    boolean hostNameVerified = false;
                    Collection<List<?>> subjectAltNames = chain[0].getSubjectAlternativeNames();
                    if (subjectAltNames != null) {
                        boolean sanVerification = false;
                        for (List<?> san : subjectAltNames) {
                            Integer nameType = (Integer)san.get(0);
                            if (nameType == 2) {
                                sanVerification = true;
                                if (!this.verifyHostName((String)san.get(1))) continue;
                                hostNameVerified = true;
                                break;
                            }
                            if (nameType != 7) continue;
                            sanVerification = true;
                            if (!this.hostName.equalsIgnoreCase((String)san.get(1))) continue;
                            hostNameVerified = true;
                            break;
                        }
                        if (sanVerification && !hostNameVerified) {
                            throw new CertificateException("Server identity verification failed. None of the DNS or IP Subject Alternative Name entries matched the server hostname/IP '" + this.hostName + "'.");
                        }
                    }
                    if (!hostNameVerified) {
                        String dn = chain[0].getSubjectX500Principal().getName("RFC2253");
                        String cn = null;
                        try {
                            LdapName ldapDN = new LdapName(dn);
                            for (Rdn rdn : ldapDN.getRdns()) {
                                if (!rdn.getType().equalsIgnoreCase("CN")) continue;
                                cn = rdn.getValue().toString();
                                break;
                            }
                        }
                        catch (InvalidNameException e) {
                            throw new CertificateException("Failed to retrieve the Common Name (CN) from the server certificate.");
                        }
                        if (!this.verifyHostName(cn)) {
                            throw new CertificateException("Server identity verification failed. The certificate Common Name '" + cn + "' does not match '" + this.hostName + "'.");
                        }
                    }
                }
            }
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            this.origTm.checkClientTrusted(chain, authType);
        }

        private boolean verifyHostName(String ptn) {
            int indexOfStar = ptn.indexOf(42);
            if (indexOfStar >= 0 && indexOfStar < ptn.indexOf(46)) {
                String head = ptn.substring(0, indexOfStar);
                String tail = ptn.substring(indexOfStar + 1);
                return StringUtils.startsWithIgnoreCase(this.hostName, head) && StringUtils.endsWithIgnoreCase(this.hostName, tail) && this.hostName.substring(head.length(), this.hostName.length() - tail.length()).indexOf(46) == -1;
            }
            return this.hostName.equalsIgnoreCase(ptn);
        }
    }

    private static class KeyStoreConf {
        public String keyStoreUrl = null;
        public String keyStorePassword = null;
        public String keyStoreType = "JKS";

        public KeyStoreConf() {
        }

        public KeyStoreConf(String keyStoreUrl, String keyStorePassword, String keyStoreType) {
            this.keyStoreUrl = keyStoreUrl;
            this.keyStorePassword = keyStorePassword;
            this.keyStoreType = keyStoreType;
        }
    }
}

