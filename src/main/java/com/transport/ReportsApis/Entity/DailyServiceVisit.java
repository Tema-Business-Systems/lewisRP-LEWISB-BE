package com.transport.ReportsApis.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "VW_DAILY_SERVICE_VISIT")
@Data
public class DailyServiceVisit {
    @Id
    @Column(name = "STORE")
    private String store;
    @Column(name = "VISIT_TYPE")
    private String visitType;
    @Column(name = "START_TIME")
    private String startTime;
    @Column(name = "END_TIME")
    private String endTime;
    @Column(name = "ACTIVITY")
    private String activity;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "VISIT_DATE")
    private Date visitDate;
    @Column(name = "SITE")
    private String site;
}
