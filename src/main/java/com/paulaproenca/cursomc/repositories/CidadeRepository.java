package com.paulaproenca.cursomc.repositories;

import com.paulaproenca.cursomc.domain.Cidade;
import com.paulaproenca.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
