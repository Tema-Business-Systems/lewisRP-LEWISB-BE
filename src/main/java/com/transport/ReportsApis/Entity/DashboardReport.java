package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vw_DashboardReport", schema = "LEWISB")
public class DashboardReport {
    @Id
    @Column(name = "metrics")
    private String metrics;
    @Column(name = "activeRoutes")
    private String activeRoutes;
    @Column(name = "vehicleLocations")
    private String vehicleLocations;
}
