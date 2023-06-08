package dsclient.dsclient.service;

import dsclient.dsclient.dto.ClientDto;
import dsclient.dsclient.entities.Client;
import dsclient.dsclient.service.impl.ClientServiceImpl;
import dsclient.dsclient.serviceExceptions.ClientException;
import dsclient.dsclient.repository.ClientRepository;
import dsclient.dsclient.serviceExceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceImpl {

    @Autowired
    private ClientRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(PageRequest page){
        Page<Client> clients = repository.findAll(page);
        return clients.map(ClientDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> entity = repository.findById(id);
        return new ClientDto(entity.orElseThrow(() -> new ClientException("Id not found")));
    }

    @Override
    @Transactional
    public ClientDto saveClient(ClientDto dto) {
        Client entity = new Client();
        dtoToClient(dto, entity);
        entity = repository.save(entity);
        return new ClientDto(entity);
    }

    @Override
    @Transactional
    public ClientDto updateClient(ClientDto dto, Long id) {
        try {
            Client entity = repository.getOne(id);
            dtoToClient(dto, entity);
            entity = repository.save(entity);
            return new ClientDto(entity);
        }catch (EntityNotFoundException e){
            throw new ClientException("Id not found");
        }
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ClientException("Id not found");
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void dtoToClient(ClientDto dto, Client entity){
            entity.setCpf(dto.getCpf());
            entity.setChildren(dto.getChildren());
            entity.setName(dto.getName());
            entity.setIncome(dto.getIncome());
            entity.setBirthDate(dto.getBirthDate());
    }



}
