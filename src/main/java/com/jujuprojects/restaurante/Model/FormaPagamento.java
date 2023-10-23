package com.jujuprojects.restaurante.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "formPagamentos")
public class FormaPagamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String tipoPag;

    @OneToMany(mappedBy = "formaPagamento",cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getTipoPag() {
        return tipoPag;
    }

    public void setTipoPag(String tipoPag) {
        this.tipoPag = tipoPag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
