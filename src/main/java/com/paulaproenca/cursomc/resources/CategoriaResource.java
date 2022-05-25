package com.paulaproenca.cursomc.resources;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.dto.CategoriaDTO;
import com.paulaproenca.cursomc.services.CategoriaService;
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
    public ResponseEntity<Categoria>save(@Valid @RequestBody CategoriaDTO objDTO) {
        Categoria obj = categoriaService.fromDTO(objDTO);
        obj = categoriaService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria>update(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            CategoriaDTO objDTO
    ) {
        Categoria obj = categoriaService.fromDTO(objDTO);
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

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>>findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Categoria>categoriaList =  categoriaService.findPage(page, linePerPage, orderby, direction);
        Page<CategoriaDTO> categoriaDTOS = categoriaList.map(CategoriaDTO::new);
        return ResponseEntity.ok().body(categoriaDTOS);
    }
}
