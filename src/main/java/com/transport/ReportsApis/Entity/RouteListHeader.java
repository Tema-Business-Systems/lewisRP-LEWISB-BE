package com.transport.ReportsApis.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Data
@Entity
@Immutable
@Table(name = "V_ROUTE_LIST_HEADER", schema = "LEWISB")
public class RouteListHeader {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "routeCode")
    private String routeCode;
    @Column(name = "vehicle")
    private String vehicle;
    @Column(name = "trip")
    private Integer trip;
    @Column(name = "driverId")
    private String driverId;
    @Column(name = "site")
    private String site;
    @Column(name = "schedDepDate")
    private String schedDepDate;
    @Column(name = "schedDepTime")
    private String schedDepTime;
    @Column(name = "schedRetDate")
    private String schedRetDate;
    @Column(name = "schedRetTime")
    private String schedRetTime;
    @Column(name = "corrDepDate")
    private Integer corrDepDate;
    @Column(name = "corrDepTime")
    private Integer corrDepTime;
    @Column(name = "corrRetDate")
    private Integer corrRetDate;
    @Column(name = "corrRetTime")
    private Integer corrRetTime;
    @Column(name = "actDepDate")
    private String actDepDate;
    @Column(name = "actDepTime")
    private String actDepTime;
    @Column(name = "actRetDate")
    private String actRetDate;
    @Column(name = "actRetTime")
    private String actRetTime;
    @Column(name = "distanceKm")
    private Long distanceKm;
    @Column(name = "timeH")
    private Double timeH;
    @Column(name = "tripDate")
    private Date tripDate;
}