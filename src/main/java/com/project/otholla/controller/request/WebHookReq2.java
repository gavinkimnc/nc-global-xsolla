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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "notification_type",
        "settings",
        "purchase",
        "user",
        "transaction",
        "payment_details"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@ToString(exclude = "additionalProperties")
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