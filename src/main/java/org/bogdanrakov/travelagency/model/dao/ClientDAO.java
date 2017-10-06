package org.bogdanrakov.travelagency.model.dao;

import org.bogdanrakov.travelagency.model.entity.Client;

import java.util.Optional;

public interface ClientDAO extends GenericDAO<Client> {

    public Optional<Client> findByLoginAndPassword(String login, String password);

}
