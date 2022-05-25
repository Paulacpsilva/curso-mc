package com.paulaproenca.cursomc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteNewDto {

    private String nome;
    private String email;
    private String cpfCnpj;
    private Long tipoCliente;

    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;
}
