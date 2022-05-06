package com.paulaproenca.cursomc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto>produtos = new ArrayList<>();

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
