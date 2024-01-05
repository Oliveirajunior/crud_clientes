package com.desafio.crud_clientes.repositories;

import com.desafio.crud_clientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
