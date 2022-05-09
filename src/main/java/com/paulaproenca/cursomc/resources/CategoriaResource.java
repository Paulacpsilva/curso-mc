package com.paulaproenca.cursomc.resources;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.dto.CategoriaDTO;
import com.paulaproenca.cursomc.services.CategoriaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(
            @PathVariable Long id
    ) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria>save(@RequestBody Categoria obj) {
        obj = categoriaService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria>update(
            @PathVariable
            Long id,
            @RequestBody
            Categoria obj
    ) {
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Categoria> delete(
            @PathVariable Long id
    ) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>>findAll() {
        List<Categoria>categoriaList =  categoriaService.findAll();
        List<CategoriaDTO> categoriaDTOS = categoriaList.stream()
                .map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOS);
    }
}
