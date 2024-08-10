package com.Monitoramento.API_Transportadora.repositories;

import com.Monitoramento.API_Transportadora.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<ProductsModel,Long> {
    Optional<ProductsModel> findByTicket(String ticket);
}
