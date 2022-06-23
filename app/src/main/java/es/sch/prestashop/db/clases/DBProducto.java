package es.sch.prestashop.db.clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DBProducto")
public class DBProducto {

    @PrimaryKey
    private Integer id;

    private String id_Category_Default;
    private String id_Default_Image;
    private String manufacturer_Name;
    private String type;
    private String price;
    private String additional_ShippingCost;
    private String date_Upd;
    private String name;
    private String description;
    private String description_Short;
    private String imagen;

    public DBProducto(Integer id, String id_Category_Default, String id_Default_Image, String manufacturer_Name, String type, String price, String additional_ShippingCost, String date_Upd, String name, String description, String description_Short) {
        this.id = id;
        this.id_Category_Default = id_Category_Default;
        this.id_Default_Image = id_Default_Image;
        this.manufacturer_Name = manufacturer_Name;
        this.type = type;
        this.price = price;
        this.additional_ShippingCost = additional_ShippingCost;
        this.date_Upd = date_Upd;
        this.name = name;
        this.description = description;
        this.description_Short = description_Short;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_Category_Default() {
        return id_Category_Default;
    }

    public void setId_Category_Default(String id_Category_Default) {
        this.id_Category_Default = id_Category_Default;
    }

    public String getId_Default_Image() {
        return id_Default_Image;
    }

    public void setId_Default_Image(String id_Default_Image) {
        this.id_Default_Image = id_Default_Image;
    }

    public String getManufacturer_Name() {
        return manufacturer_Name;
    }

    public void setManufacturer_Name(String manufacturer_Name) {
        this.manufacturer_Name = manufacturer_Name;
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

    public String getAdditional_ShippingCost() {
        return additional_ShippingCost;
    }

    public void setAdditional_ShippingCost(String additional_ShippingCost) {
        this.additional_ShippingCost = additional_ShippingCost;
    }

    public String getDate_Upd() {
        return date_Upd;
    }

    public void setDate_Upd(String date_Upd) {
        this.date_Upd = date_Upd;
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

    public String getDescription_Short() {
        return description_Short;
    }

    public void setDescription_Short(String description_Short) {
        this.description_Short = description_Short;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
