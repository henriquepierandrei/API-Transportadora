package com.Monitoramento.API_Transportadora.repositories;

import com.Monitoramento.API_Transportadora.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    Optional<OrderModel> findByCode(String code);

    Optional<OrderModel> findByTicketProduct(String ticket);
}
