package es.sch.prestashop.db.clases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DBUser {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "cookie-value")
    private String cookieValue;

    public DBUser(String firstName, String lastName, String email, String cookieValue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cookieValue = cookieValue;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
