package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "V_POD_TRACKING", schema = "LEWISB")
@Getter
@Setter
public class PodTracking {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "pod")
    private Boolean pod;
    @Column(name = "site")
    private String site;
    @Column(name = "document")
    private String document;
    @Column(name = "date")
    private Date date;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;
    @Column(name = "bp")
    private String bp;
    @Column(name = "bpName")
    private String bpName;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "deliveryMode")
    private String deliveryMode;
    @Column(name = "pack")
    private Integer pack;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "weightUnit")
    private String weightUnit;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "volumeUnit")
    private String volumeUnit;
    @Column(name = "depDate")
    private String depDate;
    @Column(name = "depTime")
    private String depTime;
    @Column(name = "arrivalDate")
    private String arrivalDate;
    @Column(name = "arrivalTime")
    private String arrivalTime;
    @Column(name = "vehicle")
    private String vehicle;
    @Column(name = "driver")
    private String driver;
    @Column(name = "reference")
    private Integer reference;
    @Column(name = "lastUpdate")
    private Integer lastUpdate;
    @Column(name = "updatedBy")
    private Integer updatedBy;
    @Column(name = "address")
    private String address;
    @Column(name = "addressLine1")
    private String addressLine1;
    @Column(name = "zip")
    private String zip;
    @Column(name = "country")
    private String country;
    @Column(name = "phone")
    private String phone;
    @Column(name = "web")
    private String web;
    @Column(name = "gpsLong")
    private String gpsLong;
    @Column(name = "gpsLat")
    private String gpsLat;
    @Column(name = "route")
    private String route;
    @Column(name = "schedule")
    private String schedule;
    @Column(name = "carrierCode")
    private String carrierCode;
    @Column(name = "carrier")
    private String carrier;
    @Column(name = "carrierEmail")
    private String carrierEmail;
    @Column(name = "grossWeight")
    private Integer grossWeight;
    @Column(name = "netWeight")
    private Integer netWeight;
    @Column(name = "nbItems")
    private Integer nbItems;
    @Column(name = "etaDate")
    private Integer etaDate;
    @Column(name = "etaTime")
    private Integer etaTime;
    @Column(name = "startUnloadingDate")
    private Integer startUnloadingDate;
    @Column(name = "startUnloadingTime")
    private Integer startUnloadingTime;
    @Column(name = "qtyUpdateDate")
    private Integer qtyUpdateDate;
    @Column(name = "qtyUpdateTime")
    private Integer qtyUpdateTime;
    @Column(name = "endUnloadingDate")
    private Integer endUnloadingDate;
    @Column(name = "endUnloadingTime")
    private Integer endUnloadingTime;
    @Column(name = "departureDate")
    private Integer departureDate;
    @Column(name = "departureTime")
    private Integer departureTime;
    @Column(name = "etdDate")
    private Integer etdDate;
    @Column(name = "etdTime")
    private Integer etdTime;
}
