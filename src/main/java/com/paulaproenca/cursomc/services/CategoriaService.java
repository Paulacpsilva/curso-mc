package com.paulaproenca.cursomc.services;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.dto.CategoriaDTO;
import com.paulaproenca.cursomc.repositories.CategoriaRepository;
import com.paulaproenca.cursomc.services.exceptions.DataIntegrityException;
import com.paulaproenca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Categoria update(Categoria obj) {
        Categoria newObj = findById(obj.getId());
        updateData(newObj, obj);
        return categoriaRepository.save(newObj);
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
    
    public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderby, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderby);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }
}
