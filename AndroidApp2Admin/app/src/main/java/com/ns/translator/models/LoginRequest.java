package com.ns.translator.models;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRequest extends AsyncTask<String, String, String> {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... uri) {
        String responseString = null;

        try {
            String urlParameter = "https://api.learnenglish.helloworldeducation.com/api/User/login";

            String strResponseData = null;
            Response response = null;

            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
            newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
            newBuilder.hostnameVerifier((hostname, session) -> true);

            OkHttpClient newClient = newBuilder.build();
            Request.Builder builder = new Request.Builder();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"mail\": \"user@user.com\",\"password\": \"user123\"}");

            Request request = builder
                    .url(urlParameter)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            response = newClient.newCall(request).execute();
            strResponseData = response.body().string();

            System.out.println("response : " + strResponseData);

        } catch (Exception e) {
            System.out.println("ERROR 1 : " + e.getMessage());
        }

        return responseString;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}