package org.bogdanrakov.travelagency.model.services;


import org.bogdanrakov.travelagency.model.dao.ClientCredentialsDAO;
import org.bogdanrakov.travelagency.model.dao.ClientDAO;
import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final ClientService INSTANCE = new ClientServiceImpl();
    }

    public static ClientService getInstance() {
        return ClientServiceImpl.Holder.INSTANCE;
    }

    @Override
    public Client getFullInfo(Client client) {
        ClientCredentialsDAO credentialsDAO = daoFactory.createClientCredentialsDAO();
        Optional<ClientCredentials> credentials = credentialsDAO.findById(client.getId());

        if (credentials.isPresent()) {
            client.setClientCredentials(credentials.get());
            credentials.get().setClient(client);
        }

        return client;
    }

    @Override
    public List<Client> getAllClients() {
        ClientDAO clientDAO = daoFactory.createClientDAO();
        ClientCredentialsDAO credentialsDAO = daoFactory.createClientCredentialsDAO();

        return clientDAO.findAll();
    }

    @Override
    public boolean disableClient(Client client) {
        ClientCredentialsDAO credentialsDAO = daoFactory.createClientCredentialsDAO();

        client.getClientCredentials().setEnabled(false);

        return credentialsDAO.update(client.getClientCredentials());
    }

    @Override
    public Optional<Client> login(String login, String password) {
        ClientDAO clientDAO = daoFactory.createClientDAO();

        return clientDAO.findByLoginAndPassword(login, password);
    }

    @Override
    public Optional<Client> register(Client client, ClientCredentials credentials) {
        Optional<Client> result = Optional.empty();

        client.setClientCredentials(credentials);
        credentials.setClient(client);

        ClientDAO clientDAO = daoFactory.createClientDAO();
        ClientCredentialsDAO credentialsDAO = daoFactory.createClientCredentialsDAO();

        if (credentialsDAO.isLoginOccupied(credentials.getEmail())) {
            return Optional.empty();
        }

        int id = clientDAO.insert(client);
        if (id != -1) {
            credentials.getClient().setId(id);
            credentialsDAO.insert(credentials);
            result = Optional.of(client);
        }

        return result;
    }

    @Override
    public Optional<Client> setDiscount(Client client, int discount) {
        return null;
    }
}
