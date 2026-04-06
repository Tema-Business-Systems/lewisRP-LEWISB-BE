package com.transport.ReportsApis.Response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderCalendarDTO {
    private String id;
    private String orderNumber;
    private String site;
    private String customer;
    private String customerName;
    private String orderType;
    private String status;
    private LocalDate orderDate;
    private String orderTime;
    private String routeNumber;
    private String vehicle;
    private String driver;
    private String origin;
    private String destination;
    private Integer totalPacks;
    private Integer totalWeight;
    private String products;
}
