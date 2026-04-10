package com.transport.ReportsApis.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitSummaryDTO {
    private int totalVisits;
    private String fieldStatus;
}
