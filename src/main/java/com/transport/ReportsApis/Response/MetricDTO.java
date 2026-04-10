package com.transport.ReportsApis.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDTO {
    private Object value;
    private String unit;
    private String trend;
    private String status;
}