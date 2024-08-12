package com.Monitoramento.API_Transportadora.models;

import com.Monitoramento.API_Transportadora.Enuns.TypeProducts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ticket;


    private TypeProducts typeProducts;

}
