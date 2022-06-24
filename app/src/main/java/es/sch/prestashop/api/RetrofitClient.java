package es.sch.prestashop.api;

import java.util.concurrent.TimeUnit;

import es.sch.prestashop.api.prestashop.ApiUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

   private static BinshopApi API_BINSHOP;
   private static PrestashopApi API_PRESTASHOP;


   private static OkHttpClient getInterceptor(){
      // Creamos un interceptor y le indicamos el log level a usar
      final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);

      // Asociamos el interceptor a las peticiones
      final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      httpClient.addInterceptor(logging);

      httpClient.connectTimeout(30, TimeUnit.SECONDS);
      httpClient.readTimeout(30, TimeUnit.SECONDS);
      httpClient.writeTimeout(30, TimeUnit.SECONDS);

      return httpClient.build();
   }

   public static BinshopApi getApiBinshop() {

      if (API_BINSHOP == null) {
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(ApiUtils.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(getInterceptor())
                 .build();

         API_BINSHOP = retrofit.create(BinshopApi.class);
      }

      return API_BINSHOP;
   }

   public static PrestashopApi getApiPrestashop() {

      if (API_PRESTASHOP == null) {
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(ApiUtils.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(getInterceptor())
                 .build();

         API_PRESTASHOP = retrofit.create(PrestashopApi.class);
      }

      return API_PRESTASHOP;
   }

}
