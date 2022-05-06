package com.paulaproenca.cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoPagamentoEnum {
    PENDENTE(1L, "Pendente"),
    QUITADO(2L, "Quitado"),
    CANCELADO(2L, "Cancelado");


    private Long codigo;
    private String tipo;

    public static EstadoPagamentoEnum toEnum(Long codigo) {
        if(codigo == null) {
            return null;
        }
        for (EstadoPagamentoEnum tipo : EstadoPagamentoEnum.values()) {
            if(codigo.equals(tipo.getCodigo())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
