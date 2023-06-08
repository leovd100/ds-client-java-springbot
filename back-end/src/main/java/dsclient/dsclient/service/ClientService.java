package dsclient.dsclient.service;

import dsclient.dsclient.dto.ClientDto;
import dsclient.dsclient.entities.Client;
import dsclient.dsclient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    
    @Transactional(readOnly = true)
    public List<ClientDto> findAll(){
        return repository.findAll().stream().map(ClientDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> entity = repository.findById(id);
        return new ClientDto(entity.get());
    }
}
