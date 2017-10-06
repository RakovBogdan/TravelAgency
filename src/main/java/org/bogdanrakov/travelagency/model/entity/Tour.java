package org.bogdanrakov.travelagency.model.entity;

import java.time.LocalDate;

public class Tour {

    private long id;
    private String title;
    private String destination;
    private String description;
    private int duration;
    private LocalDate start;
    private TourType type;
    private boolean hot;
    private int price;
    private boolean enabled;
    private int discount;

    public static class Builder {
        private long id;
        private String title;
        private String destination;
        private String description;
        private int duration;
        private LocalDate start;
        private TourType type;
        private boolean hot = false;
        private int price;
        private boolean enabled = true;
        private int discount = 0;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder start(LocalDate start) {
            this.start = start;
            return this;
        }

        public Builder tourType(TourType type) {
            this.type = type;
            return this;
        }

        public Builder hot(boolean hot) {
            this.hot = hot;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder discount(int discount) {
            this.discount = discount;
            return this;
        }

        public Tour build() {
            return new Tour(this);
        }
    }

    private Tour(Builder builder) {
        id = builder.id;
        title = builder.title;
        destination = builder.destination;
        description = builder.description;
        duration = builder.duration;
        start = builder.start;
        type = builder.type;
        hot = builder.hot;
        price = builder.price;
        enabled = builder.enabled;
        discount = builder.discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", destination='" + destination + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", start=" + start +
                ", type=" + type +
                ", hot=" + hot +
                ", price=" + price +
                ", enabled=" + enabled +
                ", discount=" + discount +
                '}';
    }
}
