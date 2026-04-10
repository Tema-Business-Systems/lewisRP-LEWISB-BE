package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class DayActivity {
    private String driverId;
    private String routeNo;
    private Long deliveries;
}
