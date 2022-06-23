package es.sch.prestashop.ui.shop;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBProducto;


public class ShopViewModel extends ViewModel {

    private LiveData<List<DBProducto>> productos;
    private final PrestaDB db;

    ShopViewModel(Application application){
        db = PrestaDB.getInstance(application);
        productos = db.productoDao().getProducts();
    }

    public LiveData<List<DBProducto>> getProductos(){
        return productos;
    }

}