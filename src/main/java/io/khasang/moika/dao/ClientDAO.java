package io.khasang.moika.dao;

import io.khasang.moika.entity.Client;

import java.util.List;

public interface ClientDAO {
    void addClient(Client client);
    void updateClient(Client client);
    List<Client> getAllClients();
    Client getClientById(long id);
    void deleteClient(Client client);
    boolean containClientById(long id);
}
