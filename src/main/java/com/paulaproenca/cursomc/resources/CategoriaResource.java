package com.paulaproenca.cursomc.resources;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Long id
    ) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);
    }
}
