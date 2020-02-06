package loc.example.dao;

import loc.example.model.Client;

import java.sql.SQLException;

public interface ClientDao {

    Client getClientByLogin(String login) throws SQLException;
    int addClient(Client client) throws SQLException;

}
