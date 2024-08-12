package com.Monitoramento.API_Transportadora.models;

import com.Monitoramento.API_Transportadora.Enuns.DeliveryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String city;
    private String date;
    private String time;
    private DeliveryStatus condition;

    private String codeOrder;
}
