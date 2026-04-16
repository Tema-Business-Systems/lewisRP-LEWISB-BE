package com.transport.ReportsApis.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "XTMSTRIPD", schema = "LEWISB")
public class TripStop {
    @EmbeddedId
    private TripStopId id;
//    @Column(name = "line_no")
//    private Integer lineNo;
//    @Column(name = "tripid")
//    private String tripid;
    @Column(name = "document")
    private String document;
    @Column(name = "sequence")
    private Integer sequence;
    @Column(name = "planned_arv_date")
    private Date plannedArvDate;
    @Column(name = "planned_arv_time")
    private String plannedArvTime;
    @Column(name = "planned_dep_date")
    private Date plannedDepDate;
    @Column(name = "planned_dep_time")
    private String plannedDepTime;
    @Column(name = "actual_arv_date")
    private Date actualArvDate;
    @Column(name = "actual_arv_time")
    private String actualArvTime;
    @Column(name = "actual_dep_date")
    private Date actualDepDate;
    @Column(name = "actual_dep_time")
    private String actualDepTime;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "SERVICETIME")
    private String servicetime;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_code")
    private String customerCode;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;
    @Column(name = "delivery")
    private String delivery;
}
