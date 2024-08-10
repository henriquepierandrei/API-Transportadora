package com.Monitoramento.API_Transportadora.controllers;

import com.Monitoramento.API_Transportadora.dtos.OrderDto;
import com.Monitoramento.API_Transportadora.dtos.ProductDto;
import com.Monitoramento.API_Transportadora.models.OrderModel;
import com.Monitoramento.API_Transportadora.models.ProductsModel;
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
        Optional<ProductsModel> productsModelOptional = this.adminService.getProductByTicket(productDto.ticket());
        if (productsModelOptional.isEmpty()){
            ProductsModel productsModel = new ProductsModel();
            productsModel.setTypeProducts(productDto.typeProducts());
            productsModel.setTicket(this.adminService.ticketGenerate());
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

    @GetMapping("/orders/{code}")
    public ResponseEntity getOrderByCode(@PathVariable(value = "code") String code){
        Optional<OrderModel> orderModelOptional = this.adminService.getOrderByCode(code);
        if (orderModelOptional.isEmpty()){return ResponseEntity.badRequest().body("No Orders founded!");}
        return ResponseEntity.status(HttpStatus.FOUND).body(orderModelOptional.get());
    }

    @PostMapping("/register/orders")
    public ResponseEntity<String> registerOrders(@RequestBody OrderDto orderDto) {
        try {
            Optional<OrderModel> orderModelOptional = this.adminService.getOrderByTicket(orderDto.ticket());
            if (orderModelOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Product with this ticket already exists.");
            }

            OrderModel orderModel = new OrderModel();
            orderModel.setDeliveryStatus(orderDto.deliveryStatus());
            orderModel.setStatus(orderDto.statusModel());
            orderModel.setTicketProduct(orderDto.ticket());

            while (true) {
                String code2 = this.adminService.code();
                if (adminService.getProductByCode(code2)) {
                    orderModel.setCode(code2);
                    break;
                }
            }

            this.adminService.saveOrder(orderModel);
            return ResponseEntity.ok("Order Registered, CODE TRACKING: " + orderModel.getCode());

        } catch (Exception e) {
            // Log the exception if needed
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }


}
