package com.transport.ReportsApis.Response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyRouteDashboardResponse {
    private LocalDate date;
    private String site;

    private Summary summary;
    private List<DriverPerformance> driverPerformance;
    private List<DayActivity> dayActivity;
    private List<RouteDetails> routeDetails;
}
