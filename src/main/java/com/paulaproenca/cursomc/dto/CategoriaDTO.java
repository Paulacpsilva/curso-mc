package com.paulaproenca.cursomc.dto;

import com.paulaproenca.cursomc.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaDTO {

    private Long id;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Size(min = 5, max = 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
