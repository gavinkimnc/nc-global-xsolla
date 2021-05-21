package com.project.otholla.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchase {

    @JsonProperty("checkout")
    public Checkout checkout;
    @JsonProperty("total")
    public Total total;


}