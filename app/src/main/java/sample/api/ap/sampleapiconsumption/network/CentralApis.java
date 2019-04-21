package sample.api.ap.sampleapiconsumption.network;

import com.google.gson.JsonObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;
import sample.api.ap.sampleapiconsumption.model.login.LoginRequest;
import sample.api.ap.sampleapiconsumption.model.verifyemail.VerifyEmailRequest;

public class CentralApis {

        private static CentralApis centralApis = null;
        private APIS apis;


        public static CentralApis getInstance(){
            if(centralApis ==  null){
                centralApis = new CentralApis();
            }
            return centralApis;
        }

        private CentralApis(){

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("TLS");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };
            try {
                if (sslContext != null) {
                    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                    okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                            .sslSocketFactory(sslContext.getSocketFactory())
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://webhook.site/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    apis = retrofit.create(APIS.class);
                }
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }

        //Facade Pattern!
        public interface APIS{

            @Headers("Content-Type: application/json")
            @POST()
            Call<JsonObject> loginRequest(@Url String url, @Header("lang") String lang, @Body LoginRequest loginRequest);

            @Headers("Content-Type: application/json")
            @GET()
            Call<JsonObject> loadDashboard(@Url String url, @Header("Authorization") String authenticationToken, @Header("lang") String lang);

            @Headers("Content-Type: application/json")
            @PUT()
            Call<JsonObject> verifyEmail(@Url String url, @Header("lang") String lang, @Body VerifyEmailRequest verifyEmailRequest);

            //Similarly for DELETE, PATCH just change the above HTTP method with them.

        }

        public APIS getAPIS(){
            return apis;
        }
}


