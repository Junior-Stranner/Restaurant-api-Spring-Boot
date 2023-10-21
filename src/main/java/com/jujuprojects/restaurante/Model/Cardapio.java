package com.jujuprojects.restaurante.Model;

import jakarta.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "cardapios")
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String comida;
    private String bebida;
    @Max(5)
    @Min(2)
    private String combo;
    @Min(0)
    private double preco;

    private double percentualDesconto;
    private double totalPreco;
    @OneToMany(mappedBy = "cardapio",cascade = CascadeType.ALL)
    List<Pedido> pedidos;

    public Cardapio(Long id, String comida, String bebida, String combo, double preco, double percentualDesconto, double totalPreco) {
        this.id = id;
        this.comida = comida;
        this.bebida = bebida;
        this.combo = combo;
        this.preco = preco;
        this.percentualDesconto = percentualDesconto;
        this.totalPreco = totalPreco;
    }

    public Cardapio() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public double getTotalPreco() {
        return totalPreco;
    }

    public void setTotalPreco(double totalPreco) {
        this.totalPreco = totalPreco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @PreRemove
    public void atualizaPedidoOnRemove(){
        System.out.println("***********atualizaPedidoOnRemove************");
        for (Pedido pedido : this.pedidos) {
            pedido.setCardapio(null);
        }
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", comida='" + comida + '\n' +
                ", bebida='" + bebida + '\'' +
                ", combo='" + combo + '\'' +
                ", preco=" + preco +
                ", percentualDesconto=" + percentualDesconto +
                ", totalPreco=" + totalPreco+
                '}';
    }
}