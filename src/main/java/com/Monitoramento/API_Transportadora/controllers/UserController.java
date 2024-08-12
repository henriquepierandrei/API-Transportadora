package com.Monitoramento.API_Transportadora.controllers;

import com.Monitoramento.API_Transportadora.models.OrderModel;
import com.Monitoramento.API_Transportadora.services.AdminService;
import com.Monitoramento.API_Transportadora.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AdminService adminService;
    private final UserService userService;

    @GetMapping("/orders/{code}")
    public ResponseEntity getOrderByCode(@PathVariable(value = "code") String code){
        try {
            OrderModel orderModel= this.userService.getOrders(code);
            return ResponseEntity.ok(orderModel);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e+HttpStatus.NOT_FOUND);
        }



    }
}
