package com.transport.ReportsApis.Response;

import lombok.Data;

import java.util.List;

@Data
public class TimelineDTO {
    private String driver;
    private String vehicle;
    private List<ActivityDTO> activities;
}
