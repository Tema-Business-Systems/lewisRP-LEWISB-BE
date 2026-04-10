package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "VW_DRIVER_ACTIVITY_TIMELINE", schema = "LEWISB")
public class DriverActivityTimeline {

    @Id
    @Column(name = "ROUTENO")
    private String routeNo;

    @Column(name = "ACTIVITY_DATE")
    private Date activityDate;

    @Column(name = "DRIVER")
    private String driver;

    @Column(name = "VEHICLE")
    private String vehicle;

    @Column(name = "SITE")
    private String site;

    @Column(name = "ACTIVITY_TYPE")
    private String activityType;

    @Column(name = "START_TIME")
    private Time startTime;

    @Column(name = "END_TIME")
    private Time endTime;

    @Column(name = "DURATION_MIN")
    private Integer durationMin;
}
