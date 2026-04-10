package com.transport.ReportsApis.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RouteDetails {
    private String routeNo;
    private LocalDateTime routeDate;
    private String driver;
    private String vehicle;
    private String site;

    private String orderNo;
    private String deliveryNo;
    private Integer sequence;
    private Integer status;

    private String customerCode;
    private String customerName;
    private String city;
    private String postalCode;

    private BigDecimal totalQty;
    private BigDecimal totalVolume;
    private String volumeUnit;
    private BigDecimal totalWeight;
    private String weightUnit;

    private BigDecimal amount;
    private String currency;

    private String latitude;
    private String longitude;
}
