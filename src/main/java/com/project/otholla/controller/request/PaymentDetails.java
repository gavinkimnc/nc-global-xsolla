package com.project.otholla.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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

}