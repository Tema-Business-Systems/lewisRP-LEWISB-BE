package com.transport.ReportsApis.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalSales {
    private BigDecimal value;
    private String currency;
}
