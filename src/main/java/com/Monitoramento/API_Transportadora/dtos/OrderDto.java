package com.Monitoramento.API_Transportadora.dtos;

import com.Monitoramento.API_Transportadora.Enuns.DeliveryStatus;
import com.Monitoramento.API_Transportadora.Enuns.TypeProducts;
import com.Monitoramento.API_Transportadora.models.ProductsModel;
import com.Monitoramento.API_Transportadora.models.StatusModel;

public record OrderDto(String ticketProduct, StatusModel statusModel, DeliveryStatus deliveryStatus) {
}
