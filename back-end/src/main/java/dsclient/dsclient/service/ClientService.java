package dsclient.dsclient.service;

import dsclient.dsclient.entities.Client;
import dsclient.dsclient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client findById(Long id) {
        Optional<Client> entity = repository.findById(id);
        return entity.get();
    }
}
