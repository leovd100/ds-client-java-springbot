package dsclient.dsclient.controller;

import dsclient.dsclient.dto.ClientDto;
import dsclient.dsclient.entities.Client;
import dsclient.dsclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ClientDto>> findAllClients(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy" , defaultValue = "id") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok().body(clientService.findAll(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }
}
