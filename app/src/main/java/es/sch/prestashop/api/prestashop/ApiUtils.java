package es.sch.prestashop.api.prestashop;

public class ApiUtils {

    public static final String BASE_URL = "https://casadelafortuna.es/";
    public static final String API_KEY = "T8748FNG4B66MS6R4GR1PEAKZJQHN9S6";

    public static final int LANGUAJE = 1;

    public static final String PRODUCTS_QUERY= "[id,id_default_image,id_category_default,manufacturer_name,type,price,additional_shipping_cost,name,description_short,description,date_upd]";
    public static final String CATEGORY_QUERY= "[id,name]";

    public static final String JSON_TYPE = "JSON";
    public static final String URL_IMAGES = "http://casadelafortuna.es/api/images/products/";
    public static final String UPDATE_CART_ITEM = "update";
}
