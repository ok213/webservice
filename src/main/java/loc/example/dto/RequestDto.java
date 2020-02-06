package loc.example.dto;

import loc.example.model.Client;

public class RequestDto {

    private String typeRequest;
    private Client client;

    public RequestDto() {
    }

    public RequestDto(String typeRequest, Client client) {
        this.typeRequest = typeRequest;
        this.client = client;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
