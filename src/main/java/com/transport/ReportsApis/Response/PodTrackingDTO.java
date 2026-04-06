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

    private List<PodLineItems> lineItems;
    private List<PodProducts> podProducts;
    private List<PodImages> podImages;
}
