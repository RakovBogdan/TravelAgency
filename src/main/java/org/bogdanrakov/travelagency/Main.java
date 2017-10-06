package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.dao.ClientDAO;
import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.services.ClientService;
import org.bogdanrakov.travelagency.model.services.ClientServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        ClientService clientService = ClientServiceImpl.getInstance();
        List<Client> clients = clientService.getAllClients();
        System.out.println(clients);

        Client volodya = clientService.getFullInfo(clients.get(2));
        System.out.println(volodya);

        if (clientService.disableClient(volodya)) {
            System.out.println();
            System.out.println(clientService.login("volodya@gmail.com", "0000"));
        };

    }
}
