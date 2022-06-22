package com.paulaproenca.cursomc.dto;

import com.paulaproenca.cursomc.services.validation.ClienteInsert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDto {

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 a 120 caracteres")
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String cpfCnpj;

    private Long tipoCliente;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String logadouro;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String numero;


    private String complemento;
    private String bairro;
    private String cep;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;
}
