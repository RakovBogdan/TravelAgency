package org.bogdanrakov.travelagency.model.entity;

public class Client {

    private long id;
    private ClientCredentials clientCredentials;
    private String name;
    private int discount;

    public static class Builder {
        private long id;
        private ClientCredentials clientCredentials;
        private String name;
        private int discount;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder clientCredentials(ClientCredentials credentials) {
            this.clientCredentials = credentials;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder discount(int discount) {
            this.discount = discount;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    private Client(Builder builder) {
        id = builder.id;
        clientCredentials = builder.clientCredentials;
        name = builder.name;
        discount = builder.discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ClientCredentials getClientCredentials() {
        return clientCredentials;
    }

    public void setClientCredentials(ClientCredentials clientCredentials) {
        this.clientCredentials = clientCredentials;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientCredentials=" + clientCredentials +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }
}
