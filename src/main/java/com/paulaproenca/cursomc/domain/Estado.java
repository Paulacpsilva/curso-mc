package com.paulaproenca.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "estado")
    private List<Cidade>cidades = new ArrayList<>();

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
