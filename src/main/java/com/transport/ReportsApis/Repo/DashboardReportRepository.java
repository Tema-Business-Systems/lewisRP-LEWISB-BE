package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DashboardReport;
import com.transport.ReportsApis.Response.DashboardReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DashboardReportResponse> getDashboardReport(String site) {

        StringBuilder sql = new StringBuilder("""
        SELECT
            site,
            title,
            value,
            icon,
            trend_value,
            trend_positive,
            status,
            dataset
        FROM LEWISB.vw_DashboardReport
    """);

//        if (site != null && !site.isEmpty()) {
//            String inSql = String.join(",", site.stream().map(s -> "'" + s + "'").toList());
//            sql.append(" WHERE site IN (").append(inSql).append(") ");
//        }
//
//        sql.append(" ORDER BY dataset, title ");
        sql.append(" WHERE site = '").append(site).append("' ");

        sql.append(" ORDER BY dataset, title ");

        return jdbcTemplate.query(
                sql.toString(),
                (rs, rowNum) -> new DashboardReportResponse(
                        rs.getString("site"),
                        rs.getString("title"),
                        rs.getInt("value"),
                        rs.getString("icon"),
                        rs.getString("trend_value"),
                        rs.getInt("trend_positive"),
                        rs.getString("status"),
                        rs.getString("dataset")
                )
        );
    }
}
