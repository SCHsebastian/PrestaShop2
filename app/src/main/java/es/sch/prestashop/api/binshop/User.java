package es.sch.prestashop.api.binshop;

import com.google.gson.annotations.Expose;

import java.util.List;

public class User {

    private String id;
    private String id_shop;
    private String id_shop_group;
    private String note;
    private String id_gender;
    private String id_default_group;
    private String id_lang;
    private String lastname;
    private String firstname;
    private String birthday;
    private String email;
    private String newsletter;
    private String ip_registration_newsletter;
    private String newsletter_date_add;
    private String optin;
    private String website;
    private String company;
    private String siret;
    private String ape;
    private String outstanding_allow_amount;
    private String show_public_prices;
    private String id_risk;
    private String max_payment_days;
    private String active;
    private String is_guest;
    private String deleted;
    private String dateAdd;
    private String dateUpd;
    private Object years;
    private Object days;
    private Object months;
    private Object geoloc_id_country;
    private Object geoloc_id_state;
    private Object geoloc_postcode;
    private Integer logged;
    private Object idGuest;
    private Object groupBox;
    private List<Object> idShopList = null;
    private Boolean forceId;

    @Expose(serialize = false, deserialize = false)
    private String cookieName;
    @Expose(serialize = false, deserialize = false)
    private String cookieValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_shop() {
        return id_shop;
    }

    public void setId_shop(String id_shop) {
        this.id_shop = id_shop;
    }

    public String getId_shop_group() {
        return id_shop_group;
    }

    public void setId_shop_group(String id_shop_group) {
        this.id_shop_group = id_shop_group;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId_gender() {
        return id_gender;
    }

    public void setId_gender(String id_gender) {
        this.id_gender = id_gender;
    }

    public String getId_default_group() {
        return id_default_group;
    }

    public void setId_default_group(String id_default_group) {
        this.id_default_group = id_default_group;
    }

    public String getId_lang() {
        return id_lang;
    }

    public void setId_lang(String id_lang) {
        this.id_lang = id_lang;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getIp_registration_newsletter() {
        return ip_registration_newsletter;
    }

    public void setIp_registration_newsletter(String ip_registration_newsletter) {
        this.ip_registration_newsletter = ip_registration_newsletter;
    }

    public String getNewsletter_date_add() {
        return newsletter_date_add;
    }

    public void setNewsletter_date_add(String newsletter_date_add) {
        this.newsletter_date_add = newsletter_date_add;
    }

    public String getOptin() {
        return optin;
    }

    public void setOptin(String optin) {
        this.optin = optin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getOutstanding_allow_amount() {
        return outstanding_allow_amount;
    }

    public void setOutstanding_allow_amount(String outstanding_allow_amount) {
        this.outstanding_allow_amount = outstanding_allow_amount;
    }

    public String getShow_public_prices() {
        return show_public_prices;
    }

    public void setShow_public_prices(String show_public_prices) {
        this.show_public_prices = show_public_prices;
    }

    public String getId_risk() {
        return id_risk;
    }

    public void setId_risk(String id_risk) {
        this.id_risk = id_risk;
    }

    public String getMax_payment_days() {
        return max_payment_days;
    }

    public void setMax_payment_days(String max_payment_days) {
        this.max_payment_days = max_payment_days;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIs_guest() {
        return is_guest;
    }

    public void setIs_guest(String is_guest) {
        this.is_guest = is_guest;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(String dateUpd) {
        this.dateUpd = dateUpd;
    }

    public Object getYears() {
        return years;
    }

    public void setYears(Object years) {
        this.years = years;
    }

    public Object getDays() {
        return days;
    }

    public void setDays(Object days) {
        this.days = days;
    }

    public Object getMonths() {
        return months;
    }

    public void setMonths(Object months) {
        this.months = months;
    }

    public Object getGeoloc_id_country() {
        return geoloc_id_country;
    }

    public void setGeoloc_id_country(Object geoloc_id_country) {
        this.geoloc_id_country = geoloc_id_country;
    }

    public Object getGeoloc_id_state() {
        return geoloc_id_state;
    }

    public void setGeoloc_id_state(Object geoloc_id_state) {
        this.geoloc_id_state = geoloc_id_state;
    }

    public Object getGeoloc_postcode() {
        return geoloc_postcode;
    }

    public void setGeoloc_postcode(Object geoloc_postcode) {
        this.geoloc_postcode = geoloc_postcode;
    }

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    public Object getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Object idGuest) {
        this.idGuest = idGuest;
    }

    public Object getGroupBox() {
        return groupBox;
    }

    public void setGroupBox(Object groupBox) {
        this.groupBox = groupBox;
    }

    public List<Object> getIdShopList() {
        return idShopList;
    }

    public void setIdShopList(List<Object> idShopList) {
        this.idShopList = idShopList;
    }

    public Boolean getForceId() {
        return forceId;
    }

    public void setForceId(Boolean forceId) {
        this.forceId = forceId;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
