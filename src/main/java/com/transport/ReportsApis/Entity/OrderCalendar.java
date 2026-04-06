package com.transport.ReportsApis.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "vw_OrderCalendar", schema = "LEWISB")
@Data
public class OrderCalendar {
    @Id
    @Column(name = "orderNumber")
    private String orderNumber;
    @Column(name = "id")
    private String id;

    @Column(name = "site")
    private String site;

    @Column(name = "customer")
    private String customer;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "orderType")
    private String orderType;

    @Column(name = "status")
    private String status;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "orderTime")
    private String orderTime;

    @Column(name = "routeNumber")
    private String routeNumber;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "driver")
    private String driver;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "totalPacks")
    private Integer totalPacks;

    @Column(name = "totalWeight")
    private Integer totalWeight;

    @Column(name = "products")
    private String products;
}
