package by.estore.entity;

import java.io.Serializable;
import java.util.Objects;

public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;

    private Short id;
    private String code;

    public Currency() {}

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) &&
                Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + code + '\'' +
                '}';
    }
}
