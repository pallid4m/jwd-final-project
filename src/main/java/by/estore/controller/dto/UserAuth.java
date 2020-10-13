package by.estore.controller.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String phone;

    public UserAuth() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return Objects.equals(email, userAuth.email) &&
                Objects.equals(password, userAuth.password) &&
                Objects.equals(phone, userAuth.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, phone);
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
