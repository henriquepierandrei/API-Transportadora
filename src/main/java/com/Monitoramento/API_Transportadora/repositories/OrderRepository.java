package com.Monitoramento.API_Transportadora.repositories;

import com.Monitoramento.API_Transportadora.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
