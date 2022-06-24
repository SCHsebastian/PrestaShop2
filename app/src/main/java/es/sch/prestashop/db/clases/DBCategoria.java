package es.sch.prestashop.db.clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import es.sch.prestashop.api.prestashop.ApiUtils;

@Entity(tableName = "DBCategoria")
public class DBCategoria {

    @PrimaryKey
    private int id;
    private String name;
    @Expose(serialize = false, deserialize = false)
    private String imagen;

    public DBCategoria(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrlImage() {
        return "https://casadelafortuna.es/api/images/categories/" + id + "/?ws_key"+ ApiUtils.API_KEY;
    }
}
