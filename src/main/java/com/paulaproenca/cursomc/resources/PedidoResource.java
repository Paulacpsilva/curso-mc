package com.paulaproenca.cursomc.resources;

import com.paulaproenca.cursomc.domain.Pedido;
import com.paulaproenca.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> buscarPorId(
            @PathVariable Long id
    ) {
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }
}
