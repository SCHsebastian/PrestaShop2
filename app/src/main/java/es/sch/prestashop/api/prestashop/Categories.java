package es.sch.prestashop.api.prestashop;

import java.util.List;

import es.sch.prestashop.db.clases.DBCategoria;

public class Categories {

    private List<DBCategoria> categories = null;

    public List<DBCategoria> getCategories() {
        return categories;
    }

    public void setCategories(List<DBCategoria> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "categories{" +
                "categories=" + categories +
                '}';
    }

}
