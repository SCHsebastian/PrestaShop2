package es.sch.prestashop.db.clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DBProducto")
public class DBProducto {

    @PrimaryKey
    private Integer id;

    private String idCategoryDefault;
    private String idDefaultImage;
    private String manufacturerName;
    private String type;
    private String price;
    private String additionalShippingCost;
    private String dateUpdate;
    private String name;
    private String description;
    private String descriptionShort;
    private String imagen;

    public DBProducto(Integer id, String idCategoryDefault, String idDefaultImage, String manufacturerName, String type, String price, String additionalShippingCost, String name, String description, String descriptionShort, String dateUpdate) {
        this.id = id;
        this.idCategoryDefault = idCategoryDefault;
        this.idDefaultImage = idDefaultImage;
        this.manufacturerName = manufacturerName;
        this.type = type;
        this.price = price;
        this.additionalShippingCost = additionalShippingCost;
        this.name = name;
        this.description = description;
        this.descriptionShort = descriptionShort;
        this.dateUpdate = dateUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCategoryDefault() {
        return idCategoryDefault;
    }

    public void setIdCategoryDefault(String idCategoryDefault) {
        this.idCategoryDefault = idCategoryDefault;
    }

    public String getIdDefaultImage() {
        return idDefaultImage;
    }

    public void setIdDefaultImage(String idDefaultImage) {
        this.idDefaultImage = idDefaultImage;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
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

    public String getAdditionalShippingCost() {
        return additionalShippingCost;
    }

    public void setAdditionalShippingCost(String additionalShippingCost) {
        this.additionalShippingCost = additionalShippingCost;
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

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
