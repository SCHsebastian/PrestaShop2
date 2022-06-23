package es.sch.prestashop.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import es.sch.prestashop.api.PrestashopApi;
import es.sch.prestashop.api.RetrofitClient;
import es.sch.prestashop.api.prestashop.ApiUtils;
import es.sch.prestashop.api.prestashop.Product;
import es.sch.prestashop.api.prestashop.Products;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.clases.DBUser;
import es.sch.prestashop.db.daos.ProductoDao;
import es.sch.prestashop.db.daos.UserDao;
import retrofit2.Call;
import retrofit2.Response;


@Database(entities = {DBUser.class, DBProducto.class}, version = 2, exportSchema = false)
public abstract class PrestaDB extends RoomDatabase {

    public static volatile PrestaDB INSTANCE;

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            ProductoDao productoDao = INSTANCE.productoDao();
            PrestashopApi prestaApi = RetrofitClient.getApiPrestashop();
            prestaApi.getProducts(ApiUtils.API_KEY, ApiUtils.JSON_TYPE, ApiUtils.PRODUCTS_QUERY).enqueue(new retrofit2.Callback<Products>() {
                @Override
                public void onResponse(Call<Products> call, Response<Products> response) {
                    if (response.isSuccessful()){
                        for (DBProducto product: response.body().getProducts()){
                            Log.d("info prod: ", product.toString());
                            productoDao.insert(product);
                        }
                    }else {
                        Log.e("PrestaDB", "Error al obtener los productos");
                    }

                }

                @Override
                public void onFailure(Call<Products> call, Throwable t) {
                    Log.w("Error", t.toString() + call.toString());
                    Log.w("Error", "error");
                }
            });
        }
    };

    public static PrestaDB getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, PrestaDB.class, "presta-db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
    public abstract ProductoDao productoDao();
}