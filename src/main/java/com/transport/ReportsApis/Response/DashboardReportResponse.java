package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DashboardReportResponse {
    private List<Map<String, Object>> metrics;
    private List<Map<String, Object>> activeRoutes;
    private List<Map<String, Object>> vehicleLocations;
}
