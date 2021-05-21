package com.project.otholla.controller.request;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "external_id",
        "dry_run",
        "agreement",
        "payment_date",
        "payment_method"
})
@Generated("jsonschema2pojo")
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
