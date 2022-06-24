package es.sch.prestashop.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.sch.prestashop.db.clases.DBCarrito;

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

    @Query("SELECT * FROM DBCarrito WHERE atribute = :atribute")
    DBCarrito getByIdProductoAtributo(int atribute);

    @Delete
    void delete(DBCarrito carrito);

    @Delete
    void deleteAll();





}
