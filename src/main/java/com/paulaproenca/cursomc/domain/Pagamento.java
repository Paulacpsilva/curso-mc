package com.paulaproenca.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paulaproenca.cursomc.domain.enums.EstadoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Pagamento {
    @Id
    private Long id;
    private Long estado;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento(Long id, EstadoPagamentoEnum estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCodigo();
        this.pedido = pedido;
    }

    public EstadoPagamentoEnum getEstado() {
        return EstadoPagamentoEnum.toEnum(estado);
    }

    public void setEstado(EstadoPagamentoEnum estado) {
        this.estado = estado.getCodigo();
    }
}
