package es.sch.prestashop.db.clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DBCarrito")
public class DBCarrito {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_producto;
    private int atribute;
    private int qty;

    public DBCarrito(int id_producto, int atribute, int qty) {
        this.id_producto = id_producto;
        this.atribute = atribute;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getAtribute() {
        return atribute;
    }

    public void setAtribute(int atribute) {
        this.atribute = atribute;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
