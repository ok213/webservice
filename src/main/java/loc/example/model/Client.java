package loc.example.model;

import java.util.Objects;

public class Client {

    private String login;
    private String password;
    private Double balance;

    public Client() {}

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
        this.balance = 0.0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(login, client.login) &&
                Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
