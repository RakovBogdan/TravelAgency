package org.bogdanrakov.travelagency.model.dao;

import org.bogdanrakov.travelagency.model.entity.ClientCredentials;

public interface ClientCredentialsDAO extends GenericDAO<ClientCredentials> {

    boolean isLoginOccupied(String login);
}
