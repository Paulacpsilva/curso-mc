package com.paulaproenca.cursomc.resources;

import com.paulaproenca.cursomc.domain.Cliente;
import com.paulaproenca.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Long id
    ) {
        Cliente Cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok().body(Cliente);
    }
}
