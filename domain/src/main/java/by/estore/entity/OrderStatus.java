package by.estore.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum State {
        PROCESSING,
        CANCELED,
        COMPLETED,
        FAILED
    }

    private Byte id;
    private String state;

    public OrderStatus() {}

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", name='" + state + '\'' +
                '}';
    }
}
