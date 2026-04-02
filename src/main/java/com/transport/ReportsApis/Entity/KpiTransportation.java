package com.transport.ReportsApis.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
@Table(name = "V_KPI_TRANSPORTATION", schema = "LEWISB")
public class KpiTransportation {
    @EmbeddedId
    private KpiTransportationId id;
    @Column(name = "nbRoutes")
    private Integer nbRoutes;
    @Column(name = "nbStops")
    private Integer nbStops;
    @Column(name = "distance")
    private Long distance;
    @Column(name = "travelTime")
    private Double travelTime;
}
