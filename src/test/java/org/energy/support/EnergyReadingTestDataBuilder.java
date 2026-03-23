package org.energy.support;

import org.energy.EnergyReading;

public final class EnergyReadingTestDataBuilder {
    private String consumer = "Коваленко Іван Петрович";
    private String supplier = "ЕнергоПостач";
    private String technician = "Сидоренко Олег Миколайович";
    private String readingDate = "2024-09-01";
    private String readingValue = "1250.5";
    private String address = "м. Київ, вул. Шевченка, 12, кв. 5";
    private String tariffZone = "Денна";
    private String pricePerKwh = "2.85";
    private String experienceYears = "5";
    private String meterType = "Електронний";

    public static EnergyReadingTestDataBuilder anEnergyReading() {
        return new EnergyReadingTestDataBuilder();
    }

    public EnergyReadingTestDataBuilder withConsumer(String value) {
        this.consumer = value;
        return this;
    }

    public EnergyReadingTestDataBuilder withSupplier(String value) {
        this.supplier = value;
        return this;
    }

    public EnergyReadingTestDataBuilder withAddress(String value) {
        this.address = value;
        return this;
    }

    public EnergyReadingTestDataBuilder withMeterType(String value) {
        this.meterType = value;
        return this;
    }

    public EnergyReading build() {
        return new EnergyReading(
            consumer,
            supplier,
            technician,
            readingDate,
            readingValue,
            address,
            tariffZone,
            pricePerKwh,
            experienceYears,
            meterType
        );
    }
}
