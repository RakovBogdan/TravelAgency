package org.bogdanrakov.travelagency.model.services;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client getFullInfo(Client client);

    List<Client> getAllClients();

    boolean disableClient(Client client);

    Optional<Client> login(String login, String password);

    Optional<Client> register(Client client, ClientCredentials credentials);

    Optional<Client> setDiscount(Client client, int discount);

}
