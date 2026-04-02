package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;

@Data
public class KpiTransportationResponse {
    private List<ChartData> chartData;
    private List<DetailRecords> detailRecords;
    private List<String> periods;
    private List<String> sites;

    @Data
    public static class ChartData {
        private String site;
        private Integer nbStops;
        private Integer nbRoutes;
        private Long distance;
        private Double travelTime;
        private String period;
    }

    @Data
    public static class DetailRecords {
        private String kpi;
        private String site;
        private String vehicle;
        private String driver;
        private Integer routes;
        private Integer stops;
        private Long distance;
        private Double travelTime;
        private String period;
    }
}
