package com.transport.ReportsApis.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DriverPerformance {
    private String driverId;
    private String vehicle;
    private BigDecimal totalSales;
    private Long deliveries;
}
