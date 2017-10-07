package org.bogdanrakov.travelagency.model.entity;

public class ClientCredentials {

    private long id;
    private Client client;
    private String email;
    private String password;
    private Role role;
    private boolean enabled;

    public static class Builder {
        private long id;
        private Client client;
        private String email;
        private String password;
        private Role role = Role.CLIENT;
        private boolean enabled = true;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public ClientCredentials build() {
            return new ClientCredentials(this);
        }

    }

    private ClientCredentials(Builder builder) {
        id = builder.id;
        client = builder.client;
        email = builder.email;
        password = builder.password;
        role = builder.role;
        enabled = builder.enabled;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ClientCredentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }
}
