package com.compusac.models.repository;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUsuario(Usuario usuario);
}
