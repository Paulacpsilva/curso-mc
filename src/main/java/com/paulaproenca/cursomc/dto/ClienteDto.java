package com.paulaproenca.cursomc.dto;

import com.paulaproenca.cursomc.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDto {

    private Long id;

    @NotNull(message = "Preenchimento Obrigatorio")
    private String nome;

    @NotNull
    private String email;

    public ClienteDto(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

}
