package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;

@Data
public class RouteListResponse {
    private List<Record> records;

    @Data
    public static class Record {
        private String id;
        private String routeCode;
        private String vehicle;
        private Integer trip;
        private String driverId;
        private String carrier;
        private String site;
        private String schedDepDate;
        private String schedDepTime;
        private String schedRetDate;
        private String schedRetTime;
        private Integer corrDepDate;
        private Integer corrDepTime;
        private Integer corrRetDate;
        private Integer corrRetTime;
        private String actDepDate;
        private String actDepTime;
        private String actRetDate;
        private String actRetTime;
        private Long distanceKm;
        private Double timeH;
        private List<Document> documents;
    }

    @Data
    public static class Document {
        private Integer sequence;
        private String documentNo;
        private String docType;
        private String arvTime;
        private String depTime;
        private String status;
    }
}
