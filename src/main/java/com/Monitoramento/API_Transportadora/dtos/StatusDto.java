package com.Monitoramento.API_Transportadora.dtos;

import com.Monitoramento.API_Transportadora.Enuns.DeliveryStatus;

public record StatusDto(String city, String date, String time, DeliveryStatus condition) {
}
