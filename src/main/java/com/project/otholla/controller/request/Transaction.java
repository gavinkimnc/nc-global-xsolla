package com.project.otholla.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("external_id")
    public String externalId;
    @JsonProperty("dry_run")
    public Integer dryRun;
    @JsonProperty("agreement")
    public Integer agreement;
    @JsonProperty("payment_date")
    public String paymentDate;
    @JsonProperty("payment_method")
    public Integer paymentMethod;


}
