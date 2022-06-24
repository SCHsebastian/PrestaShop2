package es.sch.prestashop.db.clases;

import java.util.List;

import okhttp3.Cookie;

public class Cookies {

    private List<Cookie> cookies;

    public Cookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }
}
