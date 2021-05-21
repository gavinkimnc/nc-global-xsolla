package com.project.otholla.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookReq2 {

    @JsonProperty("notification_type")
    public String notificationType;
    @JsonProperty("settings")
    public Settings settings;
    @JsonProperty("purchase")
    public Purchase purchase;
    @JsonProperty("user")
    public User user;
    @JsonProperty("transaction")
    public Transaction transaction;
    @JsonProperty("payment_details")
    public PaymentDetails paymentDetails;


}