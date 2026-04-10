package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class SummaryDTO {
    private MetricDTO avgHoursDriven;
    private MetricDTO stopsPerDay;
    private MetricDTO avgVisitDuration;
    private MetricDTO idleRatio;
}
