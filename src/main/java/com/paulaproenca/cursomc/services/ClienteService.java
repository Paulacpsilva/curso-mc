package com.paulaproenca.cursomc.services;

import com.paulaproenca.cursomc.domain.Cliente;
import com.paulaproenca.cursomc.repositories.ClienteRepository;
import com.paulaproenca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id) {
       Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       return clienteOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id));
    }
}
