package es.sch.prestashop.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.sch.prestashop.db.clases.DBProducto;

@Dao
public interface ProductoDao {

    @Query("SELECT * FROM DBProducto")
    LiveData<List<DBProducto>> getProducts();

    @Query("SELECT * FROM DBProducto WHERE id = :id")
    DBProducto getProductoById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBProducto producto);

    @Delete
    void delete(DBProducto producto);

    @Query("DELETE FROM DBProducto")
    void deleteAll();

    @Query("SELECT * FROM DBProducto WHERE idCategoryDefault = :idCategoryDefault")
    List<DBProducto> getProductoByCategory(String idCategoryDefault);

    @Query("UPDATE DBProducto SET  imagen = :url WHERE id = :id")
    void updateImagen(Integer id, String url);

    @Query("SELECT * FROM DBProducto")
    List<DBProducto> getAll();
}
