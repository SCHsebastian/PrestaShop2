package es.sch.prestashop.api;

import es.sch.prestashop.api.binshop.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BinshopApi {

 @POST("/rest/login")
 Call<BaseResponse> login(@Query(value = "email", encoded = true) String email,
                          @Query(value = "password", encoded = true) String passwd);

 @POST("/rest/register?")
 Call<BaseResponse> register(@Field(value = "email" , encoded = true) String email,
                             @Field(value = "password" , encoded = true) String passwd,
                             @Field("firstname") String firstname,
                             @Field("lastname") String lastname);

 @GET("/rest/logout")
 Call<BaseResponse> logout(@Header("Cookie-value") String cookie);

 @GET("/rest")
 Call<BaseResponse> test();

 @POST("/rest/accountedit")
 Call<BaseResponse> updateUser(@Query(value = "email" , encoded = true) String correo,
                               @Query(value = "password", encoded = true) String pass,
                               @Query("firstname") String nombre,
                               @Query("lastname")String apellidos,
                               @Query("gender") int gender);
}
