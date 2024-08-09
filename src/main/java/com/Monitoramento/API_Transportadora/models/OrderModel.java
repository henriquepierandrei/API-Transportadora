package com.Monitoramento.API_Transportadora.models;

import com.Monitoramento.API_Transportadora.Enuns.DeliveryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private StatusModel status;

    private DeliveryStatus deliveryStatus;

    private String code;

    private String ticketProduct;

}
