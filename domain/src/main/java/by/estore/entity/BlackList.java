package by.estore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BlackList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private LocalDate blackDate;

    private User user;

    public BlackList() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBlackDate() {
        return blackDate;
    }

    public void setBlackDate(LocalDate blackDate) {
        this.blackDate = blackDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return Objects.equals(id, blackList.id) &&
                Objects.equals(description, blackList.description) &&
                Objects.equals(blackDate, blackList.blackDate) &&
                Objects.equals(user, blackList.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, blackDate, user);
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", blackDate=" + blackDate +
                ", user=" + user +
                '}';
    }

    private BlackList(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.blackDate = builder.blackDate;
        this.user = builder.user;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String description;
        private LocalDate blackDate;

        private User user;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setBlackDate(LocalDate blackDate) {
            this.blackDate = blackDate;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public BlackList build() {
            return new BlackList(this);
        }
    }
}
