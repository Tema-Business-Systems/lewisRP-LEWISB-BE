package com.transport.ReportsApis.Response;

import lombok.Data;

import java.util.List;

@Data
public class DriverActivityResponseDTO {

    private String date;
    private String site;
    private SummaryDTO summary;
    private List<TimelineDTO> timeline;
}
