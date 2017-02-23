package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;

import java.util.Date;
import java.util.List;


public interface ClientDao {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);
    Client getClientById(int id);
    Client getClientByName(String firstName, String middelName, String lastName);
    Client getClientByTel(String tel);
    List<Car> getCarsByClient(Client client);
    List<Client> getClientsList();
    List<Client> getClientsListByLastDateWash(Date dateStart, Date dateEnd);
    List<Client> getClientListByStatus(int status);
}
