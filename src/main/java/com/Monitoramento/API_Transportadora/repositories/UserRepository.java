package com.Monitoramento.API_Transportadora.repositories;

import com.Monitoramento.API_Transportadora.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByCpfAndPhone(String cpf, String phone);
}
