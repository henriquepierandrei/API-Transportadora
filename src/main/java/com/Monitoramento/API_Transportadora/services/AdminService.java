package com.Monitoramento.API_Transportadora.services;

import com.Monitoramento.API_Transportadora.models.OrderModel;
import com.Monitoramento.API_Transportadora.models.ProductsModel;
import com.Monitoramento.API_Transportadora.repositories.OrderRepository;
import com.Monitoramento.API_Transportadora.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;



    public List<ProductsModel> getAllProducts(){
        return productsRepository.findAll();
    }

    public Optional<ProductsModel> getProductByTicket(String ticket){
        return productsRepository.findByTicket(ticket);
    }

    public boolean getProductByTicketBoolean(String ticket){
        Optional<ProductsModel> productsModelOptional = this.productsRepository.findByTicket(ticket);
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


    public void saveProduct(ProductsModel productsModel){
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

    public String code(){
        Random random = new Random();
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < 8; i++){
            char letraAleatoria = (char) (random.nextInt(26) + 'A');
            int numbers = random.nextInt(10);
            list.add(String.valueOf(letraAleatoria));
            list.add(String.valueOf(numbers));
        }
        Collections.shuffle(list);
        for(String val : list){
            stringBuilder.append(val);
        }
        stringBuilder.append("BR");


        System.out.println(stringBuilder);
        return stringBuilder.toString();

    }

    public boolean getProductByCode(String code){
        Optional<OrderModel> order = this.orderRepository.findByCode(code);
        if (order.isEmpty()){return true;}
        return false;

    }

    public void saveOrder(OrderModel orderModel){
        this.orderRepository.save(orderModel);
    }
}
