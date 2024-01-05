package com.desafio.crud_clientes.services;

import com.desafio.crud_clientes.dtos.ClientDTO;
import com.desafio.crud_clientes.entities.Client;
import com.desafio.crud_clientes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Page<ClientDTO> findAll(Pageable pageable){
       Page<Client> clients = repository.findAll(pageable);
       return clients.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO save (ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update (Long id, ClientDTO dto){
        Client entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
