package org.energy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meters")
public class Meter {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "meter_type_id")
    private MeterType meterType;

    @Column(name = "installed_at")
    private LocalDate installedAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Consumer getConsumer() { return consumer; }
    public void setConsumer(Consumer consumer) { this.consumer = consumer; }
    public MeterType getMeterType() { return meterType; }
    public void setMeterType(MeterType meterType) { this.meterType = meterType; }
    public LocalDate getInstalledAt() { return installedAt; }
    public void setInstalledAt(LocalDate installedAt) { this.installedAt = installedAt; }
}
