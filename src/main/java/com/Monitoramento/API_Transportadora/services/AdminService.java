package com.Monitoramento.API_Transportadora.services;

import com.Monitoramento.API_Transportadora.models.OrderModel;
import com.Monitoramento.API_Transportadora.models.ProductsModel;
import com.Monitoramento.API_Transportadora.repositories.OrderRepository;
import com.Monitoramento.API_Transportadora.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;


    public List<ProductsModel> getAllProducts(){
        return productsRepository.findAll();
    }

    public Optional<ProductsModel> getProductByTicket(String ticket){
        return productsRepository.findByTicketIdentification(ticket);
    }

    public boolean getProductByTicketBoolean(String ticket){
        Optional<ProductsModel> productsModelOptional = this.productsRepository.findByTicketIdentification(ticket);
        if (productsModelOptional.isEmpty()){
            return true;
        }
        return false;

    }


    public String ticketGenerate(){
        Random random = new Random();
        StringBuilder ticket = new StringBuilder();

        while(true){
            for (int i = 0; i < 15; i ++){
                int number = random.nextInt(10);
                ticket.append(String.valueOf(number));
            }
            if (getProductByTicketBoolean(ticket.toString())){
                return ticket.toString();
            }
            ticket.setLength(0);

        }


    }


    public void save(ProductsModel productsModel){
        this.productsRepository.save(productsModel);
    }

    public List<OrderModel> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<OrderModel> getOrderByCode(String code){
        return this.orderRepository.findByCode(code);
    }


    public Optional<OrderModel> getOrderByTicket(String ticket) {
        return this.orderRepository.findByTicketProduct(ticket);
    }

    public String generateCode() {
    }
}
