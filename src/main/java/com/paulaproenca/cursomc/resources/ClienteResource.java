package com.paulaproenca.cursomc.resources;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.domain.Cliente;
import com.paulaproenca.cursomc.dto.CategoriaDTO;
import com.paulaproenca.cursomc.dto.ClienteDto;
import com.paulaproenca.cursomc.dto.ClienteNewDto;
import com.paulaproenca.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente>save(@Valid @RequestBody ClienteNewDto objDTO) {
        Cliente obj = clienteService.fromDTO(objDTO);
        obj = clienteService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarPorId(
            @PathVariable Long id
    ) {
        Cliente Cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(Cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente>update(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            ClienteDto objDTO
    ) {
        Cliente obj = clienteService.fromDTO(objDTO);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> delete(
            @PathVariable Long id
    ) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>>findAll() {
        List<Cliente>clienteList =  clienteService.findAll();
        List<ClienteDto> clienteDtoS = clienteList.stream()
                .map(ClienteDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDtoS);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDto>>findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Cliente>clienteList = clienteService.findPage(page, linePerPage, orderby, direction);
        Page<ClienteDto> clienteDtoS = clienteList.map(ClienteDto::new);
        return ResponseEntity.ok().body(clienteDtoS);
    }
}
