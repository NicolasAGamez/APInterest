package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;

// La clase representa la respuesta para las tasas de interés
@JsonInclude(JsonInclude.Include.NON_NULL)
class InterestRatesResponse {

    // Interés de crédito
    private Double creditInterest;
    // Interés mensual de crédito
    private Double monthlyCreditInterest;
    // Interés de intermediación
    private Double betweenness;
    // Interés de administración
    private Double administration;
    // Iva
    private Double ivaAdministration;

    
    // Constructores
    public InterestRatesResponse() {
    }

    public InterestRatesResponse(Double creditInterest, Double monthlyCreditInterest, Double betweenness, Double administration, Double ivaAdministration) {
        this.creditInterest = creditInterest;
        this.monthlyCreditInterest = monthlyCreditInterest;
        this.betweenness = betweenness;
        this.administration = administration;
        this.ivaAdministration = ivaAdministration;
    }

    
    // Getters y Setters
    public Double getCreditInterest() {
        return creditInterest;
    }

    public void setCreditInterest(Double creditInterest) {
        this.creditInterest = creditInterest;
    }
    
    public Double getMonthlyCreditInterest() {
        return monthlyCreditInterest;
    }

    public void setMonthlyCreditInterest(Double creditInterest) {
        this.monthlyCreditInterest = monthlyCreditInterest;
    }

    public Double getBetweenness() {
        return betweenness;
    }

    public void setBetweenness(Double betweenness) {
        this.betweenness = betweenness;
    }

    public Double getAdministration() {
        return administration;
    }

    public void setAdministration(Double administration) {
        this.administration = administration;
    }

    public Double getIvaAdministration() {
        return ivaAdministration;
    }

    public void setIvaAdministration(Double ivaAdministration) {
        this.ivaAdministration = ivaAdministration;
    }
}
