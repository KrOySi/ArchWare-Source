/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.protocol;

import com.mysql.cj.protocol.ExportControlled;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkResources {
    private final Socket mysqlConnection;
    private final InputStream mysqlInput;
    private final OutputStream mysqlOutput;

    public NetworkResources(Socket mysqlConnection, InputStream mysqlInput, OutputStream mysqlOutput) {
        this.mysqlConnection = mysqlConnection;
        this.mysqlInput = mysqlInput;
        this.mysqlOutput = mysqlOutput;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void forceClose() {
        block27: {
            block25: {
                try {
                    if (ExportControlled.isSSLEstablished(this.mysqlConnection)) break block25;
                    try {
                        if (this.mysqlInput != null) {
                            this.mysqlInput.close();
                        }
                    }
                    finally {
                        if (this.mysqlConnection != null && !this.mysqlConnection.isClosed() && !this.mysqlConnection.isInputShutdown()) {
                            try {
                                this.mysqlConnection.shutdownInput();
                            }
                            catch (UnsupportedOperationException unsupportedOperationException) {}
                        }
                    }
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
            try {
                if (ExportControlled.isSSLEstablished(this.mysqlConnection)) break block27;
                try {
                    if (this.mysqlOutput != null) {
                        this.mysqlOutput.close();
                    }
                }
                finally {
                    if (this.mysqlConnection != null && !this.mysqlConnection.isClosed() && !this.mysqlConnection.isOutputShutdown()) {
                        try {
                            this.mysqlConnection.shutdownOutput();
                        }
                        catch (UnsupportedOperationException unsupportedOperationException) {}
                    }
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        try {
            if (this.mysqlConnection != null) {
                this.mysqlConnection.close();
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}

