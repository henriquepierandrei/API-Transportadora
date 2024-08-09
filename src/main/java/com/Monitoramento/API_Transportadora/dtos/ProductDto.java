package com.Monitoramento.API_Transportadora.dtos;

import com.Monitoramento.API_Transportadora.Enuns.TypeProducts;

public record ProductDto(String ticket, TypeProducts typeProducts) {
}
