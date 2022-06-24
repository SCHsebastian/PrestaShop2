package es.sch.prestashop.ui.shop;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.sch.prestashop.R;
import es.sch.prestashop.api.BinshopApi;
import es.sch.prestashop.api.RetrofitClient;
import es.sch.prestashop.api.binshop.BaseResponse;
import es.sch.prestashop.api.prestashop.ApiUtils;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCarrito;
import es.sch.prestashop.db.clases.DBProducto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopViewModel extends ViewModel {

    private static final String TAG = "ShopViewModel";

    private LiveData<List<DBProducto>> productos;
    private final MutableLiveData<String> condicionBusqueda;
    private final PrestaDB db;
    private final BinshopApi api;
    private final Application application;

    ShopViewModel(Application application){
        db = PrestaDB.getInstance(application);
        productos = db.productoDao().getProducts();
        api = RetrofitClient.getApiBinshop();
        this.application = application;

        condicionBusqueda = new MutableLiveData<>();
        condicionBusqueda.setValue("");

        productos = Transformations.switchMap(condicionBusqueda,
                db.productoDao()::getProductoByCategory);
    }

    public LiveData<List<DBProducto>> getProductos(){
        return productos;
    }

    public void setCondicionBusqueda(String id){
        condicionBusqueda.setValue(id);
    }


    public void insertarProducto(DBProducto producto) {
        DBCarrito carrito = new DBCarrito(producto.getId(), producto.getCache_default_attribute(), 1);
        db.carritoDao().insert(carrito);
        api.cartItem(carrito.getId(),carrito.getId_producto(), carrito.getAtribute(), "up", ApiUtils.UPDATE_CART_ITEM, carrito.getQty()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(), R.string.cart_add_succesfully, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),R.string.cart_adding_error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),R.string.cart_adding_error, Toast.LENGTH_LONG).show();
                Log.e(TAG,"Error al añadir el producto al carrito");
            }
        });
    }
}