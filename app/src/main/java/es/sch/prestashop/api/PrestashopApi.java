package es.sch.prestashop.api;

import es.sch.prestashop.api.prestashop.Categories;
import es.sch.prestashop.api.prestashop.Products;
import es.sch.prestashop.db.clases.DBProducto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PrestashopApi {

   //PRODUCTS

   @GET("/api/products")
   Call<Products> getProducts(@Query("ws_key") String ws_key,
                              @Query("output_format") String output_format,
                              @Query("display") String display);
                                     /* @Query("filter[id_category]") String id_category,
                                      @Query("filter[id_manufacturer]") String id_manufacturer,
                                      @Query("filter[id_supplier]") String id_supplier,*/

   @GET("/api/products/{id}")
   Call<DBProducto> getProduct(@Path("id") int id, @Query("ws_key") String ws_key);


   //CATEGORIES

    @GET("/api/categories")
   Call<Categories> getCategories(@Query("ws_key") String ws_key,
                                  @Query("output_format") String output_format,
                                  @Query("display") String display);


    //IMAGES
    @GET("/api/images/categories/{id}")
    Call<ResponseBody> getImage(@Path("id") int id, @Query("ws_key") String ws_key);

    @GET("/api/images/products/{id}/{imageId}")
    Call<ResponseBody> getImage(@Path("id") int id, @Path("imageId") int imageId, @Query("ws_key") String ws_key);

}
