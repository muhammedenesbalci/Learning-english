package com.ns.translator.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.io.IOException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginViewModel extends ViewModel {
    private String mMail;
    private String mPassword;
    MutableLiveData<String> token = new MutableLiveData<>();

    public LiveData<String> getData() {
        requestTry();
        return token;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }
    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    private void requestTry() {
        try {
            String urlParameter = "https://api.learnenglish.helloworldeducation.com/api/User/login";

            String email = mMail;
            String password = mPassword;

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

            System.out.println("EMAIL : " + email);
            System.out.println("PASSWORD : " + password);

            String requestText = "{\"mail\": \"" + email + "\",\"password\": \"" + password + "\"}";

            RequestBody body = RequestBody.create(mediaType, requestText);

            Request request = builder
                    .url(urlParameter)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            newClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("ERROR : " + e.getMessage());
                    token.postValue("empty");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        String strResponseData = response.body().string();
                        token.postValue(strResponseData);
                    } else {
                        token.postValue("empty");
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("ERROR 1 : " + e.getMessage());
            token.setValue("empty");
        }
    }
}