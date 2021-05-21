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
        "payment",
        "payment_method_sum",
        "xsolla_balance_sum",
        "payout",
        "xsolla_fee",
        "payment_method_fee",
        "vat",
        "sales_tax",
        "direct_wht",
        "payout_currency_rate",
        "repatriation_commission"
})
@Generated("jsonschema2pojo")
public class PaymentDetails {

    @JsonProperty("payment")
    public Payment payment;
    @JsonProperty("payment_method_sum")
    public PaymentMethodSum paymentMethodSum;
    @JsonProperty("xsolla_balance_sum")
    public XsollaBalanceSum xsollaBalanceSum;
    @JsonProperty("payout")
    public Payout payout;
    @JsonProperty("xsolla_fee")
    public XsollaFee xsollaFee;
    @JsonProperty("payment_method_fee")
    public PaymentMethodFee paymentMethodFee;
    @JsonProperty("vat")
    public Vat vat;
    @JsonProperty("sales_tax")
    public SalesTax salesTax;
    @JsonProperty("direct_wht")
    public DirectWht directWht;
    @JsonProperty("payout_currency_rate")
    public String payoutCurrencyRate;
    @JsonProperty("repatriation_commission")
    public RepatriationCommission repatriationCommission;
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