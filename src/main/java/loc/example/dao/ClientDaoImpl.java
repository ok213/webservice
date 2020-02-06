package loc.example.dao;

import loc.example.model.Client;
import loc.example.util.C3p0DataSource;

import java.sql.*;

public class ClientDaoImpl implements ClientDao {

    @Override
    public Client getClientByLogin(String login) throws SQLException {
        Client client = new Client();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users WHERE login=?;";
            Connection connection = C3p0DataSource.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.executeQuery();
            rs = stmt.getResultSet();
            while (rs.next()) {
                String loginFromTable = rs.getString("login");
                String password = rs.getString("password");
                Double balance = rs.getDouble("balance");
                client.setLogin(loginFromTable);
                client.setPassword(password);
                client.setBalance(balance);
            }
        } finally {
            rs.close();
            stmt.close();
        }
        return client;
    }

    @Override
    public int addClient(Client client) throws SQLException {
        int row = 0;
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO users(login, password, balance) VALUES(?, ?, 0.0);";
            Connection connection = C3p0DataSource.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getLogin());
            stmt.setString(2, client.getPassword());
            row = stmt.executeUpdate();
        } finally {
            stmt.close();
        }
        return row;
    }

}
