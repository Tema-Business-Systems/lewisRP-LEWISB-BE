package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class DashboardMetricDTO {
    private String title;
    private Integer value;
    private String status;
}
