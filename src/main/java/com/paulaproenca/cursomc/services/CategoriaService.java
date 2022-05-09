package com.paulaproenca.cursomc.services;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.repositories.CategoriaRepository;
import com.paulaproenca.cursomc.services.exceptions.DataIntegrityException;
import com.paulaproenca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Long id) {
       Optional<Categoria> CategoriaOptional = categoriaRepository.findById(id);
       return CategoriaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id));
    }

    public Categoria save(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        findById(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        try {
            findById(id);
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria>findAll() {
      return categoriaRepository.findAll();
    }
}
