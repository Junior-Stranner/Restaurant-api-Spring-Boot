package com.jujuprojects.restaurante.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mesa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente _id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "formaPagamento_id")
    private FormaPagamento formaPagamento;

    public Pedido(int mesa, Cardapio cardapio, Cliente cliente) {
        this.mesa = mesa;
        this.cardapio = cardapio;
        this.cliente = cliente;
    }

    public Pedido() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public Cardapio getCardapio(Cardapio cardapio) {
        return this.cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", mesa=" + mesa +
                ", cardapio=" + cardapio +
                ", cliente=" + cliente +
                '}';
    }
}
