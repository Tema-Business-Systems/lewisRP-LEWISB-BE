package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DashboardReportResponse {
    private List<DashboardMetricDTO> metrics;
    private List<ActiveRouteDTO> activeRoutes;
    private List<VehicleLocationDTO> vehicleLocations;
}
