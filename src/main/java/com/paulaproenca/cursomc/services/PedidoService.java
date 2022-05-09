package com.paulaproenca.cursomc.services;

import com.paulaproenca.cursomc.domain.Pedido;
import com.paulaproenca.cursomc.repositories.PedidoRepository;
import com.paulaproenca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Long id) {
       Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
       return pedidoOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id));
    }
}
