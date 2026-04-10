package com.transport.ReportsApis.Response;

import lombok.Data;

import java.util.List;

@Data
public class DailyServiceVisitResponseDTO {
    private VisitFiltersDTO filters;
    private VisitSummaryDTO summary;
    private List<ServiceVisitLogDTO> serviceVisitLog;
}
