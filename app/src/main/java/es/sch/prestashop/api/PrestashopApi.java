package es.sch.prestashop.api;

import java.util.List;

import es.sch.prestashop.api.prestashop.Products;
import es.sch.prestashop.db.clases.DBProducto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PrestashopApi {

   @GET("/api/products")
   Call<Products> getProducts(@Query("ws_key") String ws_key,
                              @Query("output_format") String output_format,
                              @Query("display") String display);
                                     /* @Query("filter[id_category]") String id_category,
                                      @Query("filter[id_manufacturer]") String id_manufacturer,
                                      @Query("filter[id_supplier]") String id_supplier,*/

   @GET("/api/products/{id}")
   Call<DBProducto> getProduct(@Path("id") int id, @Query("ws_key") String ws_key);

}
