package com.Monitoramento.API_Transportadora.controllers;

import com.Monitoramento.API_Transportadora.models.ProductsModel;
import com.Monitoramento.API_Transportadora.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity getAllProducts(){
        List<ProductsModel> userModelList = this.adminService.getAllProducts();
        if (userModelList.isEmpty()){return ResponseEntity.badRequest().body("No products registered!");}
        return ResponseEntity.status(HttpStatus.FOUND).body(userModelList);
    }

    @GetMapping("/users")
    public ResponseEntity getProductByticket(@RequestParam String ticket){
        Optional<ProductsModel> productsModel = this.adminService.getProductByTicket(ticket);
        if (productsModel.isEmpty()){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No products registered with the code!");}
        return ResponseEntity.status(HttpStatus.FOUND).body(productsModel.get());

    }

}
