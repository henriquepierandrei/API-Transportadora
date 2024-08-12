package com.Monitoramento.API_Transportadora.controllers;

import com.Monitoramento.API_Transportadora.dtos.*;
import com.Monitoramento.API_Transportadora.models.OrderModel;
import com.Monitoramento.API_Transportadora.models.ProductsModel;
import com.Monitoramento.API_Transportadora.models.StatusModel;
import com.Monitoramento.API_Transportadora.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<ProductsModel> userModelList = this.adminService.getAllProducts();
        if (userModelList.isEmpty()){return ResponseEntity.badRequest().body("No products registered!");}
        return ResponseEntity.status(HttpStatus.FOUND).body(userModelList);
    }

    @GetMapping("/products/{ticket}")
    public ResponseEntity getProductByticket(@PathVariable(value = "ticket") String ticket){
        Optional<ProductsModel> productsModel = this.adminService.getProductByTicket(ticket);
        if (productsModel.isEmpty()){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No products registered with the code!");}
        return ResponseEntity.status(HttpStatus.FOUND).body(productsModel.get());

    }

    @PostMapping("/register/product")
    public ResponseEntity registerProduct(@RequestBody ProductDto productDto){
        Optional<ProductsModel> productsModelOptional = this.adminService.getProductByTicketAndTypeProducts(productDto.ticket(), productDto.typeProducts());
        if (productsModelOptional.isEmpty()){
            ProductsModel productsModel = new ProductsModel();
            productsModel.setTypeProducts(productDto.typeProducts());
            productsModel.setTicket(productDto.ticket());
            this.adminService.saveProduct(productsModel);
            return ResponseEntity.status(HttpStatus.OK).body("Product registered, Ticket Product: ["+productsModel.getTicket()+"]");
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/orders")
    public ResponseEntity getAllOrders(){
        List<OrderModel> orderModelList = this.adminService.getAllOrders();
        if (orderModelList.isEmpty()){return ResponseEntity.badRequest().body("No Orders exists!");}
        return ResponseEntity.status(HttpStatus.FOUND).body(orderModelList);
    }



    @PostMapping("/register/orders")
    public ResponseEntity<String> registerOrders(@RequestBody RegisterOrderDto registerOrderDto) {
        try {
            // Extrair OrderDto e StatusDto do RegisterOrderDto
            OrderDto orderDto = registerOrderDto.orderDto();
            StatusDto statusDto = registerOrderDto.statusDto();

            // Verificar se o pedido já existe
            Optional<OrderModel> orderModelOptional = this.adminService.getOrderByTicket(orderDto.ticketProduct());
            if (orderModelOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Product with this ticket already exists.");
            }

            // Criar e configurar OrderModel e StatusModel
            OrderModel orderModel = new OrderModel();
            StatusModel statusModel = new StatusModel();

            while (true) {
                String code2 = this.adminService.code();
                if (adminService.getProductByCode(code2)) {
                    orderModel.setCode(code2);
                    statusModel.setCodeOrder(code2);
                    break;
                }
            }

            // Configurar StatusModel com base no StatusDto
            statusModel.setCity(statusDto.city());
            statusModel.setTime(statusDto.time());
            statusModel.setDate(statusDto.date());
            statusModel.setCondition(statusDto.condition());


            orderModel.setStatus(statusModel);
            orderModel.setTicketProduct(orderDto.ticketProduct());

            this.adminService.saveStatus(statusModel);


            this.adminService.saveOrder(orderModel);
            return ResponseEntity.ok("Order Registered, CODE TRACKING: " + orderModel.getCode());

        } catch (Exception e) {
            // Log the exception if needed
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }



}
