package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class ServiceVisitLogDTO {
    private String store;
    private String visitType;
    private String start;
    private String end;
    private String activity;
    private String status;
    private String date;
    private String site;
}
