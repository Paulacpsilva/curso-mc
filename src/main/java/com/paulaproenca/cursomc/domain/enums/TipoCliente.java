package com.paulaproenca.cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoCliente {
    PESSOA_FISICA(1L, "Pessoa Fisica"),
    PESSOA_JURIDICA(2L, "Pessoa Juridico");

    private Long codigo;
    private String tipo;

    public static TipoCliente toEnum(Long codigo) {
        if(codigo == null) {
            return null;
        }
        for (TipoCliente tipoCliente : TipoCliente.values()) {
            if(codigo.equals(tipoCliente.getCodigo())){
                return tipoCliente;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
