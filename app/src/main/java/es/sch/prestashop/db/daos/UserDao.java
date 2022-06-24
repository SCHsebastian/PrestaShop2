package es.sch.prestashop.db.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import es.sch.prestashop.db.clases.DBUser;

@Dao
public interface UserDao {
    @Query("SELECT * FROM DBUser")
    DBUser getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DBUser user);

    @Delete
    void delete(DBUser user);
}
