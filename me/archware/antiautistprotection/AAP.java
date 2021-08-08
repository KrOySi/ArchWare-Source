/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  okhttp3.MediaType
 *  okhttp3.OkHttpClient
 *  okhttp3.Request
 *  okhttp3.Request$Builder
 *  okhttp3.RequestBody
 */
package me.archware.antiautistprotection;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AAP {
    private static final OkHttpClient httpClient = new OkHttpClient();

    public static void log(String discord) throws IOException {
        Request request = new Request.Builder().url("https://raw.githubusercontent.com/dreamnights1/ArchWare/main/blacklist.arch").build();
        if (Arrays.asList(httpClient.newCall(request).execute().body().string().split("\n")).contains(AAP.getHWID())) {
            System.exit(0);
        }
        request = new Request.Builder().url("https://discord.com/api/webhooks/849687511331897364/U_rUd2H4kVJQeMIzCfO6njv1kVHp4kQqZ0zIDP3NLS_K6DblA2CZpxIkNYRdUr9rFCZg").post(RequestBody.create((MediaType)MediaType.parse((String)"application/json; charset=utf-8"), (String)("{\n  \"embeds\": [\n    {\n      \"title\": \"AntiAutistProtection\",\n      \"description\": \"\",\n      \"color\": 4983434,\n      \"fields\": [\n        {\n          \"name\": \"Username\",\n          \"value\": \"" + System.getProperty("user.name") + "\",\n          \"inline\": true\n        },\n        {\n          \"name\": \"HWID\",\n          \"value\": \"" + AAP.getHWID() + "\",\n          \"inline\": true\n        },\n        {\n          \"name\": \"Time\",\n          \"value\": \"" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + "\"\n        },\n        {\n          \"name\": \"Discord\",\n          \"value\": \"" + discord + "\"\n        }\n      ]\n    }\n  ]\n}"))).build();
        httpClient.newCall(request).execute();
    }

    private static String getHWID() {
        return Base64.getEncoder().encodeToString((System.getProperty("user.name") + Runtime.getRuntime().availableProcessors() + System.getProperty("os.name") + System.getProperty("os.arch") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME")).getBytes());
    }
}

