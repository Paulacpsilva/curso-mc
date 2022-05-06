package com.paulaproenca.cursomc.services;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.repositories.CategoriaRepository;
import com.paulaproenca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Long id) {
       Optional<Categoria> CategoriaOptional = categoriaRepository.findById(id);
       return CategoriaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id));
    }
}
