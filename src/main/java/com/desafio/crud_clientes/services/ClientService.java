package com.desafio.crud_clientes.services;

import com.desafio.crud_clientes.dtos.ClientDTO;
import com.desafio.crud_clientes.entities.Client;
import com.desafio.crud_clientes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly= true)
    public ClientDTO findById(Long id){
       Client client = repository.findById(id).get();
       return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Stream<ClientDTO> findAll(){
       List<Client> clients = repository.findAll();
       return clients.stream().map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO save (ClientDTO dto){
        Client entity = new Client();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

}
