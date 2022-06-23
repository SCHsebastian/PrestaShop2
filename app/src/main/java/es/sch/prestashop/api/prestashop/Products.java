package es.sch.prestashop.api.prestashop;

import java.util.List;

import es.sch.prestashop.db.clases.DBProducto;

public class Products{

    private List<DBProducto> products = null;

    public List<DBProducto> getProducts() {
        return products;
    }

    public void setProducts(List<DBProducto> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "Products{" +
                "productoList=" + products +
                '}';
    }
}
