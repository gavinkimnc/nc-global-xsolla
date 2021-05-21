package com.project.otholla.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesTax {

    @JsonProperty("currency")
    public String currency;
    @JsonProperty("amount")
    public Integer amount;
    @JsonProperty("percent")
    public Integer percent;


}