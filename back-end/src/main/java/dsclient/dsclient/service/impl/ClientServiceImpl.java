package dsclient.dsclient.service.impl;

import dsclient.dsclient.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ClientServiceImpl {

    Page<ClientDto> findAll(PageRequest page);
    ClientDto findById(Long id);
    ClientDto saveClient(ClientDto dto);
    ClientDto updateClient(ClientDto dto, Long id);

    void deleteClient(Long id);

}
