package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "VW_DRIVER_ACTIVITY_SUMMARY", schema = "LEWISB")
public class DriverActivitySummary {

    @Id
    @Column(name = "ACTIVITY_DATE")
    private Date activityDate;

    @Column(name = "DRIVER")
    private String driver;

    @Column(name = "AVG_HOURS_DRIVEN")
    private Double avgHoursDriven;

    @Column(name = "STOPS_PER_DAY")
    private Integer stopsPerDay;

    @Column(name = "AVG_VISIT_DURATION")
    private Integer avgVisitDuration;

    @Column(name = "IDLE_RATIO")
    private Integer idleRatio;
}
