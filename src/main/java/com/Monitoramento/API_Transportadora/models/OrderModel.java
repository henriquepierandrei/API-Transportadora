package com.Monitoramento.API_Transportadora.models;

import com.Monitoramento.API_Transportadora.Enuns.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private StatusModel status;



    private String code;

    private String ticketProduct;

}
