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
import es.sch.prestashop.api.prestashop.Categories;
import es.sch.prestashop.api.prestashop.Products;
import es.sch.prestashop.db.clases.DBCategoria;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.clases.DBUser;
import es.sch.prestashop.db.daos.CategoriaDao;
import es.sch.prestashop.db.daos.ProductoDao;
import es.sch.prestashop.db.daos.UserDao;
import es.sch.prestashop.utils.DescargarImagen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Database(entities = {DBUser.class, DBProducto.class, DBCategoria.class}, version = 1, exportSchema = false)
public abstract class PrestaDB extends RoomDatabase {

    public static String TAG = "PrestaDB";

    public static volatile PrestaDB INSTANCE;

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            CategoriaDao categoriaDao = INSTANCE.categoriaDao();
            PrestashopApi api = RetrofitClient.getApiPrestashop();
            api.getCategories(ApiUtils.API_KEY, ApiUtils.JSON_TYPE,ApiUtils.CATEGORY_QUERY).enqueue(new retrofit2.Callback<Categories>() {
                @Override
                public void onResponse(Call<Categories> call, Response<Categories> response) {
                    if (response.isSuccessful()) {
                        List<DBCategoria> categories = response.body().getCategories();
                        categoriaDao.insertAll(categories);
                    } else {
                        Log.e(TAG, "Error al obtener las categorias");
                    }
                }
                @Override
                public void onFailure(Call<Categories> call, Throwable t) {
                    Log.w(TAG, t.toString() + call);
                    Log.w(TAG, "Error al obtener las categorias, la consulta ha fallado");
                }
            });
            ProductoDao productoDao = INSTANCE.productoDao();
            PrestashopApi prestaApi = RetrofitClient.getApiPrestashop();
            prestaApi.getProducts(ApiUtils.API_KEY, ApiUtils.JSON_TYPE, ApiUtils.PRODUCTS_QUERY).enqueue(new retrofit2.Callback<Products>() {
                @Override
                public void onResponse(Call<Products> call, Response<Products> response) {
                    if (response.isSuccessful()){
                        List<DBProducto> productos = response.body().getProducts();
                        productoDao.insertAll(productos);
                    }else {
                        Log.e(TAG, "Error al obtener los productos");
                    }

                }

                @Override
                public void onFailure(Call<Products> call, Throwable t) {
                    Log.w(TAG, t.toString() + call);
                    Log.w(TAG, "Error al obtener los productos, la consulta ha fallado");
                }
            });
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

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
    public abstract CategoriaDao categoriaDao();
}