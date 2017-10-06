package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;
import org.bogdanrakov.travelagency.model.entity.Role;
import org.bogdanrakov.travelagency.model.services.ClientService;
import org.bogdanrakov.travelagency.model.services.ClientServiceImpl;

public class TestRegister {

    public static void main(String[] args) {
        ClientService clientService = new ClientServiceImpl();
        Client client = new Client.Builder()
                .name("Tester")
                .discount(0)
                .build();
        ClientCredentials credentials = new ClientCredentials.Builder()
                .email("tester@gmail.com")
                .password("0000")
                .role(Role.CLIENT)
                .enabled(true)
                .build();

        System.out.println(clientService.register(client, credentials));
        System.out.println();
        System.out.println(clientService.getAllClients());

    }
}
