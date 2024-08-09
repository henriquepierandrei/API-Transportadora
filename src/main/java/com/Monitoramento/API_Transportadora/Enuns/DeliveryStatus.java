package com.Monitoramento.API_Transportadora.Enuns;


public enum DeliveryStatus {
    PENDING,
    IN_TRANSIT,
    ARRIVED_AT_DEPOT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    RETURNED_TO_SENDER,
    FAILED_ATTEMPT,
    CANCELED;
}