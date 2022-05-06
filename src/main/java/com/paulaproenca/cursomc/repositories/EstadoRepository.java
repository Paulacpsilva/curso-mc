package com.paulaproenca.cursomc.repositories;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
