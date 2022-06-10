package com.compusac.models.service;

import java.util.List;
import java.util.Optional;

import com.compusac.models.entity.Usuario;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Order;

public interface IOrderService {
    List<Order> findAll();

    Order findById(Long id);

    Order create(Order order);

    Order update(Order order, Long id);

    void delete(Long id);

    String generarNumeroOrden();

    List<Order> findByUsuario(Usuario usuario) throws NotFoundException;
}
