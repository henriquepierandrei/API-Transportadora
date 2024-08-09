package com.Monitoramento.API_Transportadora.services;

import com.Monitoramento.API_Transportadora.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public Optional<UserModel> getUserByEmail(String email){
        Optional<UserModel> userModel = this.userRepository.findByEmail(email);
        return userModel;
    }

    public void save(UserModel user){
        this.userRepository.save(user);
    }
}