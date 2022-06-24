package es.sch.prestashop.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.sch.prestashop.db.clases.DBCategoria;

@Dao
public interface CategoriaDao {

    @Insert
    void insert(DBCategoria categoria);

    @Query("SELECT * FROM DBCategoria")
    List<DBCategoria> getAll();

    @Query("SELECT * FROM DBCategoria WHERE id = :id")
    DBCategoria getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DBCategoria> categories);

    @Update
    void update(DBCategoria categoria);
}
