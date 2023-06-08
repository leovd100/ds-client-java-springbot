package dsclient.dsclient.controller;

import dsclient.dsclient.entities.Client;
import dsclient.dsclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/server")
    public ResponseEntity<String> checkServer(){
        return ResponseEntity.ok().body("Server on");
    }

    @GetMapping()
    public ResponseEntity<List<Client>> findAllClients(){
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }
}
