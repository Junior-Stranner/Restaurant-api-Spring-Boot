package com.jujuprojects.restaurante.Repository;

import com.jujuprojects.restaurante.Model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository <Cliente,Long> {
}
