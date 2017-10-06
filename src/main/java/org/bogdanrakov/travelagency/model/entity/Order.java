package org.bogdanrakov.travelagency.model.entity;

import java.time.LocalDateTime;

public class Order {

    private long id;
    private Client client;
    private Tour tour;
    private LocalDateTime date;
    private int toursAmount;
    private OrderStatus status;

    public static class Builder {
        private long id;
        private Client client;
        private Tour tour;
        private LocalDateTime date;
        private int toursAmount;
        private OrderStatus status;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder tour(Tour tour) {
            this.tour = tour;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder toursAmount(int amount) {
            this.toursAmount = amount;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }

    private Order(Builder builder) {
        id = builder.id;
        client = builder.client;
        tour = builder.tour;
        date = builder.date;
        toursAmount = builder.toursAmount;
        status = builder.status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getToursAmount() {
        return toursAmount;
    }

    public void setToursAmount(int toursAmount) {
        this.toursAmount = toursAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", tour=" + tour +
                ", date=" + date +
                ", toursAmount=" + toursAmount +
                ", status=" + status +
                '}';
    }
}
