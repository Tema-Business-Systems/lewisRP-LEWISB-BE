package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "VW_DAILY_ROUTE_SUMMARY")
@Data
public class DailyRouteSummary {
    @Id
    @Column(name = "DELIVERYNO")
    private String deliveryNo;
    @Column(name = "ROUTENO")
    private String routeNo;
    @Column(name = "ORDERNO")
    private String orderNo;
    @Column(name = "SEQUENCE")
    private Integer sequence;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "CUSTOMERCODE")
    private String customerCode;
    @Column(name = "CUSTOMER")
    private String customer;
    @Column(name = "CITY")
    private String city;
    @Column(name = "POSTAL")
    private String postal;
    @Column(name = "TOTALQTY")
    private BigDecimal totalQty;
    @Column(name = "TOTALVOL")
    private BigDecimal totalVol;
    @Column(name = "VOLUNIT")
    private String volUnit;
    @Column(name = "TOTALWGT")
    private BigDecimal totalWgt;
    @Column(name = "WGTUNIT")
    private String wgtUnit;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "CURRENCYUNIT")
    private String currencyUnit;
    @Column(name = "LAT")
    private String lat;
    @Column(name = "LNG")
    private String lng;
    @Column(name = "ROUTEDATE")
    private LocalDateTime routeDate;
    @Column(name = "DRIVER")
    private String driver;
    @Column(name = "VEHICLE")
    private String vehicle;
    @Column(name = "SITE")
    private String site;
}
