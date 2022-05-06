package com.paulaproenca.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulaproenca.cursomc.domain.enums.EstadoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagamentoBoleto extends Pagamento {

    @JsonFormat(pattern = "dd/MM/yyy")
    private Date dataVencimento;
    private Date datapagamento;

    public PagamentoBoleto(Long id, EstadoPagamentoEnum estado, Pedido pedido, Date dataVencimento, Date datapagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.datapagamento = datapagamento;
    }

}
