package org.energy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EnergyReadingDTO {
    private String consumerName;
    private String supplierName;
    private String technicianName;
    private LocalDate readingDate;
    private BigDecimal readingValue;
    private String address;
    private String tariffZone;
    private BigDecimal pricePerKwh;
    private int experienceYears;
    private String meterType;

    // Constructor
    public EnergyReadingDTO(String consumerName, String supplierName, String technicianName,
                             LocalDate readingDate, BigDecimal readingValue, String address,
                             String tariffZone, BigDecimal pricePerKwh,
                             int experienceYears, String meterType) {
        this.consumerName = consumerName;
        this.supplierName = supplierName;
        this.technicianName = technicianName;
        this.readingDate = readingDate;
        this.readingValue = readingValue;
        this.address = address;
        this.tariffZone = tariffZone;
        this.pricePerKwh = pricePerKwh;
        this.experienceYears = experienceYears;
        this.meterType = meterType;
    }

    // Getters and Setters
    public String getConsumerName() { return consumerName; }
    public void setConsumerName(String consumerName) { this.consumerName = consumerName; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getTechnicianName() { return technicianName; }
    public void setTechnicianName(String technicianName) { this.technicianName = technicianName; }

    public LocalDate getReadingDate() { return readingDate; }
    public void setReadingDate(LocalDate readingDate) { this.readingDate = readingDate; }

    public BigDecimal getReadingValue() { return readingValue; }
    public void setReadingValue(BigDecimal readingValue) { this.readingValue = readingValue; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTariffZone() { return tariffZone; }
    public void setTariffZone(String tariffZone) { this.tariffZone = tariffZone; }

    public BigDecimal getPricePerKwh() { return pricePerKwh; }
    public void setPricePerKwh(BigDecimal pricePerKwh) { this.pricePerKwh = pricePerKwh; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    public String getMeterType() { return meterType; }
    public void setMeterType(String meterType) { this.meterType = meterType; }
}
