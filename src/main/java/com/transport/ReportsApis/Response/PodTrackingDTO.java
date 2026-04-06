package com.transport.ReportsApis.Response;

import com.transport.ReportsApis.Entity.PodImages;
import com.transport.ReportsApis.Entity.PodLineItems;
import com.transport.ReportsApis.Entity.PodProducts;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PodTrackingDTO {
    private String id;
    private Boolean pod;
    private String site;
    private String document;
    private String date;
    private String type;
    private String status;
    private String bp;
    private String bpName;
    private String city;
    private String state;
    private String deliveryMode;
    private Integer pack;
    private Integer weight;
    private String weightUnit;
    private Integer volume;
    private String volumeUnit;
    private String depDate;
    private String depTime;
    private String arrivalDate;
    private String arrivalTime;
    private String vehicle;
    private String driver;
    private Integer reference;
    private Integer lastUpdate;
    private Integer updatedBy;
    private String address;
    private String addressLine1;
    private String zip;
    private String country;
    private String phone;
    private String web;
    private String gpsLong;
    private String gpsLat;
    private String route;
    private String schedule;
    private String carrierCode;
    private String carrier;
    private String carrierEmail;
    private Integer grossWeight;
    private Integer netWeight;
    private Integer nbItems;
    private Integer etaDate;
    private Integer etaTime;
    private Integer startUnloadingDate;
    private Integer startUnloadingTime;
    private Integer qtyUpdateDate;
    private Integer qtyUpdateTime;
    private Integer endUnloadingDate;
    private Integer endUnloadingTime;
    private Integer departureDate;
    private Integer departureTime;
    private Integer etdDate;
    private Integer etdTime;
    private List<PodLineItems> lineItems;
    private List<PodProducts> podProducts;
    private List<PodImages> podImages;
}
