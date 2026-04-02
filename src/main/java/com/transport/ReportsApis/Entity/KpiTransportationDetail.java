package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
@Table(name = "V_KPI_TRANSPORTATION_DETAIL", schema = "LEWISB")
public class KpiTransportationDetail {
    @Id
    @Column(name = "tripid")
    private String tripId;
    @Column(name = "site")
    private String site;
    @Column(name = "VEHICLE")
    private String vehicle;
    @Column(name = "DRIVER")
    private String driver;
    @Column(name = "stops")
    private Integer stops;
    @Column(name = "routes")
    private Integer routes;
    @Column(name = "distance")
    private Long distance;
    @Column(name = "travelTime")
    private Double travelTime;
    @Column(name = "period")
    private String period;
}
