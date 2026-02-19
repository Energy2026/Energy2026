package org.energy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "meter_readings")
public class MeterReading {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "tariff_zone_id")
    private TariffZone tariffZone;

    @Column(name = "reading_date")
    private LocalDate readingDate;

    @Column(name = "reading_value")
    private BigDecimal readingValue;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Meter getMeter() { return meter; }
    public void setMeter(Meter meter) { this.meter = meter; }
    public Technician getTechnician() { return technician; }
    public void setTechnician(Technician technician) { this.technician = technician; }
    public TariffZone getTariffZone() { return tariffZone; }
    public void setTariffZone(TariffZone tariffZone) { this.tariffZone = tariffZone; }
    public LocalDate getReadingDate() { return readingDate; }
    public void setReadingDate(LocalDate readingDate) { this.readingDate = readingDate; }
    public BigDecimal getReadingValue() { return readingValue; }
    public void setReadingValue(BigDecimal readingValue) { this.readingValue = readingValue; }
}
