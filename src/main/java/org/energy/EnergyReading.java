package org.energy;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "energy-readings")
public class EnergyReading {

    @Id
    private String id;

    private String consumer;
    private String supplier;
    private String technician;
    private String reading_date;
    private String reading_value;
    private String address;
    private String tariff_zone;
    private String price_per_kwh;
    private String experience_years;
    private String meter_type;

    // Конструктор без аргументів (потрібен для Spring Data)
    public EnergyReading() {}

    // Конструктор з усіма полями
    public EnergyReading(String consumer, String supplier, String technician,
                          String reading_date, String reading_value, String address,
                          String tariff_zone, String price_per_kwh,
                          String experience_years, String meter_type) {
        this.consumer = consumer;
        this.supplier = supplier;
        this.technician = technician;
        this.reading_date = reading_date;
        this.reading_value = reading_value;
        this.address = address;
        this.tariff_zone = tariff_zone;
        this.price_per_kwh = price_per_kwh;
        this.experience_years = experience_years;
        this.meter_type = meter_type;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getConsumer() { return consumer; }
    public void setConsumer(String consumer) { this.consumer = consumer; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    public String getTechnician() { return technician; }
    public void setTechnician(String technician) { this.technician = technician; }

    public String getReading_date() { return reading_date; }
    public void setReading_date(String reading_date) { this.reading_date = reading_date; }

    public String getReading_value() { return reading_value; }
    public void setReading_value(String reading_value) { this.reading_value = reading_value; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTariff_zone() { return tariff_zone; }
    public void setTariff_zone(String tariff_zone) { this.tariff_zone = tariff_zone; }

    public String getPrice_per_kwh() { return price_per_kwh; }
    public void setPrice_per_kwh(String price_per_kwh) { this.price_per_kwh = price_per_kwh; }

    public String getExperience_years() { return experience_years; }
    public void setExperience_years(String experience_years) { this.experience_years = experience_years; }

    public String getMeter_type() { return meter_type; }
    public void setMeter_type(String meter_type) { this.meter_type = meter_type; }

    @Override
    public String toString() {
        return "EnergyReading { id=\"" + id + "\"\n" +
               " consumer=\"" + consumer + "\"\n" +
               " supplier=\"" + supplier + "\"\n" +
               " technician=\"" + technician + "\"\n" +
               " reading_date=\"" + reading_date + "\"\n" +
               " reading_value=\"" + reading_value + "\"\n" +
               " address=\"" + address + "\"\n" +
               " tariff_zone=\"" + tariff_zone + "\"\n" +
               " price_per_kwh=\"" + price_per_kwh + "\"\n" +
               " experience_years=\"" + experience_years + "\"\n" +
               " meter_type=\"" + meter_type + "\"\n" +
               "}";
    }
}
