package com.Monitoramento.API_Transportadora.services;


import com.Monitoramento.API_Transportadora.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;

    public boolean registerIsValid(String cpf, String phone){
        Optional<UserModel> userModelOptional = this.userRepository.findByCpfAndPhone(cpf, phone);
        if (userModelOptional.isEmpty()){return true;}
        return false;
    }


}
