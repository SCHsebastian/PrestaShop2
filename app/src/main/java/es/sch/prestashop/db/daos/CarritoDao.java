package es.sch.prestashop.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.sch.prestashop.db.clases.DBCarrito;
import es.sch.prestashop.db.clases.DBProducto;

@Dao
public interface CarritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBCarrito carrito);

    @Insert
    void insertAll(List<DBCarrito> carritos);

    @Query("SELECT * FROM DBCarrito")
    List<DBCarrito> getAll();

    @Query("SELECT * FROM DBCarrito WHERE id = :id")
    DBCarrito getById(int id);

    @Query("SELECT * FROM dbcarrito WHERE id_producto = :item_id")
    DBCarrito getByIdProducto(int item_id);

    @Query("SELECT * FROM DBCarrito WHERE atribute = :atribute")
    DBCarrito getByIdProductoAtributo(int atribute);

    @Delete
    void delete(DBCarrito carrito);

    @Query("DELETE FROM DBCarrito")
    void deleteAll();

    @Query("SELECT * FROM DBCarrito ORDER BY id DESC LIMIT 1")
    DBCarrito getLast();

    @Query("SELECT DBProducto.* FROM DBProducto INNER JOIN DBCarrito ON DBCarrito.id_producto = DBProducto.id ")
    LiveData<List<DBProducto>> getAllProductos();
}
