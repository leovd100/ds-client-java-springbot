package dsclient.dsclient.controller;

import dsclient.dsclient.dto.ClientDto;
import dsclient.dsclient.service.ClientService;
import dsclient.dsclient.service.impl.ClientServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @ApiOperation(value = "This method is used to get all clients.")
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

    @ApiOperation(value = "This method is used to get a client by id.")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @ApiOperation(value = "This method is used to save a client.")
    @PostMapping()
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(dto));
    }

    @ApiOperation(value = "This method is used to update a client by id.")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id, @RequestBody ClientDto dto){
        return ResponseEntity.ok().body(clientService.updateClient(dto, id));
    }
    @ApiOperation(value = "This method is used to delete a client by id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
