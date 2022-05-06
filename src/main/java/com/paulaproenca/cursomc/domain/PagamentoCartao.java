package com.paulaproenca.cursomc.domain;

import com.paulaproenca.cursomc.domain.enums.EstadoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagamentoCartao extends Pagamento {

    private Integer numeroParcelas;

    public PagamentoCartao(Long id, EstadoPagamentoEnum estado, Pedido pedido, Integer numeroParcelas) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }

}
