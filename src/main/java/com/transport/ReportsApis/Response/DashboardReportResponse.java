package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;
import java.util.Map;

public class DashboardReportResponse {
    private String site;
    private String title;
    private Integer value;
    private String icon;
    private String trendValue;
    private Integer trendPositive;
    private String status;
    private String dataset;

    public DashboardReportResponse() {
    }

    public DashboardReportResponse(
            String site,
            String title,
            Integer value,
            String icon,
            String trendValue,
            Integer trendPositive,
            String status,
            String dataset
    ) {
        this.site = site;
        this.title = title;
        this.value = value;
        this.icon = icon;
        this.trendValue = trendValue;
        this.trendPositive = trendPositive;
        this.status = status;
        this.dataset = dataset;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTrendValue() {
        return trendValue;
    }

    public void setTrendValue(String trendValue) {
        this.trendValue = trendValue;
    }

    public Integer getTrendPositive() {
        return trendPositive;
    }

    public void setTrendPositive(Integer trendPositive) {
        this.trendPositive = trendPositive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }
}
