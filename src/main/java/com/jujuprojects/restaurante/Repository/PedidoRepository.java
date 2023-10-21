package com.jujuprojects.restaurante.Repository;

import com.jujuprojects.restaurante.Model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository <Pedido, Long> {
}
