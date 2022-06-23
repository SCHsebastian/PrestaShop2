package es.sch.prestashop.api.binshop;


public class Psdata {

    private String status;
    private String message;
    private String customerId;
    private int sessionData;
    private int cartCount;
    private User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getSessionData() {
        return sessionData;
    }

    public void setSessionData(int sessionData) {
        this.sessionData = sessionData;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
