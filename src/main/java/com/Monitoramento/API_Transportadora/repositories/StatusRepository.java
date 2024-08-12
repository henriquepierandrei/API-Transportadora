package com.Monitoramento.API_Transportadora.repositories;

import com.Monitoramento.API_Transportadora.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusModel,Long> {
}
