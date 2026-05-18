package com.transport.ReportsApis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "vw_DashboardReport", schema = "LEWISB")
@Getter
@Setter
public class DashboardReport {

    @Id
    @Column(name = "site")
    private String site;   // ⚠️ fake ID (OK for read-only)
    @Column(name = "report_date")
    @Temporal(TemporalType.DATE)
    private Date reportDate;
    @Column(name = "title")
    private String title;
    @Column(name = "value")
    private Integer value;
    @Column(name = "icon")
    private String icon;
    @Column(name = "trend_value")
    private String trendValue;
    @Column(name = "trend_positive")
    private Integer trendPositive;
    @Column(name = "status")
    private String status;
    @Column(name = "dataset")
    private String dataset;
}
