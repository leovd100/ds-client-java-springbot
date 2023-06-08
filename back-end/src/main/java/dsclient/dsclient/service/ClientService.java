package dsclient.dsclient.service;

import dsclient.dsclient.dto.ClientDto;
import dsclient.dsclient.entities.Client;
import dsclient.dsclient.serviceExceptions.ClientException;
import dsclient.dsclient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(PageRequest page){
        Page<Client> clients = repository.findAll(page);
        return clients.map(ClientDto::new);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> entity = repository.findById(id);
        return new ClientDto(entity.orElseThrow(() -> new ClientException("Id not found")));
    }

    @Transactional
    public ClientDto saveClient(ClientDto dto) {
        Client entity = new Client();
        dtoToClient(dto, entity);
        entity = repository.save(entity);
        return new ClientDto(entity);
    }

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


    private void dtoToClient(ClientDto dto, Client entity){
            entity.setCpf(dto.getCpf());
            entity.setChildren(dto.getChildren());
            entity.setName(dto.getName());
            entity.setIncome(dto.getIncome());
            entity.setBirthDate(dto.getBirthDate());
    }



}
