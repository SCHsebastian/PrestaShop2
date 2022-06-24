package es.sch.prestashop.db.clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

import es.sch.prestashop.api.prestashop.ApiUtils;

@Entity(tableName = "DBProducto")
public class DBProducto {

    @PrimaryKey
    private Integer id;
    @SerializedName(value = "id_category_default")
    private String id_category_default;
    @SerializedName(value = "id_default_image")
    private String id_default_image;
    @SerializedName(value = "manufacturer_name")
    private String manufacturer_name;
    private String type;
    private String price;
    @SerializedName(value = "additional_shipping_cost")
    private String additional_shipping_cost;
    @SerializedName(value = "date_upd")
    private String date_upd;
    private String name;
    private String description;
    @SerializedName(value = "description_short")
    private String description_short;
    @Expose(serialize = false, deserialize = false)
    private String imagen;

    public DBProducto(Integer id, String id_category_default, String id_default_image, String manufacturer_name, String type, String price, String additional_shipping_cost, String date_upd, String name, String description, String description_short) {
        this.id = id;
        this.id_category_default = id_category_default;
        this.id_default_image = id_default_image;
        this.manufacturer_name = manufacturer_name;
        this.type = type;
        this.price = price;
        this.additional_shipping_cost = additional_shipping_cost;
        this.date_upd = date_upd;
        this.name = name;
        this.description = description;
        this.description_short = description_short;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_category_default() {
        return id_category_default;
    }

    public void setId_category_default(String id_category_default) {
        this.id_category_default = id_category_default;
    }

    public String getId_default_image() {
        return id_default_image;
    }

    public void setId_default_image(String id_default_image) {
        this.id_default_image = id_default_image;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getAdditional_shipping_cost() {
        return additional_shipping_cost;
    }

    public void setAdditional_shipping_cost(String additional_shippingCost) {
        this.additional_shipping_cost = additional_shippingCost;
    }

    public String getDate_upd() {
        return date_upd;
    }

    public void setDate_upd(String date_upd) {
        this.date_upd = date_upd;
    }

    public String getDescription_short() {
        return description_short;
    }

    public void setDescription_short(String description_short) {
        this.description_short = description_short;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public File getUrlImagen(){
        return null;
    }

}
