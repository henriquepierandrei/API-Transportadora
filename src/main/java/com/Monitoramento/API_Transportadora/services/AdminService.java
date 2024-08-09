package com.Monitoramento.API_Transportadora.services;

import com.Monitoramento.API_Transportadora.models.ProductsModel;
import com.Monitoramento.API_Transportadora.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ProductsRepository productsRepository;


    public List<ProductsModel> getAllProducts(){
        return productsRepository.findAll();
    }

    public Optional<ProductsModel> getProductByTicket(String ticket){
        return productsRepository.findByTicketIdentification(ticket);
    }

}
