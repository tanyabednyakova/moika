package io.khasang.moika.dao;

import io.khasang.moika.entity.Client;

public interface ClientDao {
    Client getClientById(long id);
}
