package com.nzt.paymentService.model;

public class Charge {
    private Long amount;
    private String currency;
    private String description;
    private String source;

    public Charge() {

    }

    public Charge(Long amount, String currency, String description, String source) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.source = source;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
