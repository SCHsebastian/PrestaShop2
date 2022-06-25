package es.sch.prestashop.ui.notifications;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import es.sch.prestashop.api.BinshopApi;
import es.sch.prestashop.api.RetrofitClient;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCarrito;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.daos.ProductoDao;

public class NotificationsViewModel extends ViewModel {

    private LiveData<List<DBProducto>> productos;
    private final PrestaDB db;
    private final BinshopApi api;


    public NotificationsViewModel(Application application) {
        db = PrestaDB.getInstance(application);
        productos = db.carritoDao().getAllProductos();
        api = RetrofitClient.getApiBinshop();
    }

    public LiveData<List<DBProducto>> getProductos() {
        return productos;
    }

    public void eliminarProducto(DBProducto producto) {
        DBCarrito carrito = db.carritoDao().getByIdProducto(producto.getId());
        db.carritoDao().delete(carrito);
    }

    public void eliminarTodo() {
        db.carritoDao().deleteAll();
    }

    public float getPrecioTotal(){
        float total = 0;
        List<DBCarrito> carritos = db.carritoDao().getAll();
        ProductoDao productoDao = db.productoDao();
        for (DBCarrito carrito : carritos) {
            DBProducto producto = productoDao.getProductoById(carrito.getId_producto());
            total += Float.parseFloat(producto.getPrice()) * carrito.getQty();
        }
        return total;
    }
}