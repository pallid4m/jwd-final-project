package by.estore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Order implements Serializable {
    private static final long serialVersionUID = 8744227046050815683L;

    private Long id;
    private LocalDateTime createDate;
    private BigDecimal amount;

    private User user;
    private OrderStatus orderStatus;
    private Currency currency;

    private Set<Product> products;

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(createDate, order.createDate) &&
                Objects.equals(amount, order.amount) &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(currency, order.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, amount, user, orderStatus, currency);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", amount=" + amount +
                ", user=" + user +
                ", orderStatus=" + orderStatus +
                ", currency=" + currency +
                '}';
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.createDate = builder.createDate;
        this.amount = builder.amount;
        this.user = builder.user;
        this.orderStatus = builder.orderStatus;
        this.currency = builder.currency;
        this.products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private LocalDateTime createDate;
        private BigDecimal amount;

        private User user;
        private OrderStatus orderStatus;
        private Currency currency;

        private Set<Product> products;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder setProducts(Set<Product> products) {
            this.products = products;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
