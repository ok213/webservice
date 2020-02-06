package loc.example.service;

import loc.example.dao.ClientDao;
import loc.example.dao.ClientDaoImpl;
import loc.example.dto.RequestDto;
import loc.example.model.Client;
import loc.example.util.StringToXMLParser;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private ClientDao clientDao;

    public UserServiceImpl() {
        this.clientDao = new ClientDaoImpl();
    }

    @Override
    public String getResponse(String request) {
        RequestDto requestDto = StringToXMLParser.parse(request);
        if ("CREATE-AGT".equals(requestDto.getTypeRequest())) {
            return createUser(requestDto);
        }
        if ("GET-BALANCE".equals(requestDto.getTypeRequest())) {
            return getBalance(requestDto);
        }
        return null;
    }

    private String createUser(RequestDto requestDto) {
        Client clientRequest = requestDto.getClient();
        try {
            Client clientFromBase = clientDao.getClientByLogin(requestDto.getClient().getLogin());
            if (clientFromBase.getLogin() == null) {
                // создаем нового с нулевым балансом
                int row = clientDao.addClient(clientRequest);
                if (row == 1) {
                    return getMessage(0);
                }
            } else if (clientFromBase.getLogin().equals(clientRequest.getLogin())) {
                return getMessage(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getMessage(2);
    }

    private String getBalance(RequestDto requestDto) {
        try {
            Client clientFromBase = clientDao.getClientByLogin(requestDto.getClient().getLogin());
            if (clientFromBase.getLogin() == null) {
                return getMessage(3);
            } else if (!clientFromBase.getPassword().equals(requestDto.getClient().getPassword())) {
                return getMessage(4);
            }
            return getMessage(0, clientFromBase.getBalance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getMessage(2);
    }

    private String getMessage(int code) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?><response><result-code>");
        sb.append(code);
        sb.append("</result-code></response>");
        return sb.toString();
    }

    private String getMessage(int code, double balance) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?><response><result-code>");
        sb.append(code);
        sb.append("</result-code><extra name=\"balance\">");
        sb.append(balance);
        sb.append("</extra></result-code></response>");
        return sb.toString();
    }

}
