package es.sch.prestashop.api.prestashop;


public class Product {

    private Long id;
    private String idCategoryDefault;
    private String idDefaultImage;
    private String manufacturerName;
    private String type;
    private String price;
    private String additionalShippingCost;
    private String dateUpd;
    private String name;
    private String description;
    private String descriptionShort;

    public Product(Long id, String idCategoryDefault, String idDefaultImage, String manufacturerName, String type, String price, String additionalShippingCost, String dateUpd, String name, String description, String descriptionShort) {
        this.id = id;
        this.idCategoryDefault = idCategoryDefault;
        this.idDefaultImage = idDefaultImage;
        this.manufacturerName = manufacturerName;
        this.type = type;
        this.price = price;
        this.additionalShippingCost = additionalShippingCost;
        this.dateUpd = dateUpd;
        this.name = name;
        this.description = description;
        this.descriptionShort = descriptionShort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(String dateUpd) {
        this.dateUpd = dateUpd;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Product.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("idCategoryDefault");
        sb.append('=');
        sb.append(((this.idCategoryDefault == null)?"<null>":this.idCategoryDefault));
        sb.append(',');
        sb.append("idDefaultImage");
        sb.append('=');
        sb.append(((this.idDefaultImage == null)?"<null>":this.idDefaultImage));
        sb.append(',');
        sb.append("manufacturerName");
        sb.append('=');
        sb.append(((this.manufacturerName == null)?"<null>":this.manufacturerName));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("additionalShippingCost");
        sb.append('=');
        sb.append(((this.additionalShippingCost == null)?"<null>":this.additionalShippingCost));
        sb.append(',');
        sb.append("dateUpd");
        sb.append('=');
        sb.append(((this.dateUpd == null)?"<null>":this.dateUpd));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("descriptionShort");
        sb.append('=');
        sb.append(((this.descriptionShort == null)?"<null>":this.descriptionShort));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
