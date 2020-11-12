package by.estore.entity;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private User user;
    private Order order;

    private Integer quantity;

    public OrderDetails() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "user=" + user +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}
