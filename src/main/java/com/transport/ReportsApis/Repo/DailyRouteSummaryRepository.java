package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DailyRouteSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyRouteSummaryRepository extends JpaRepository<DailyRouteSummary, String> {

    @Query(value = """
        SELECT *
        FROM LEWISB.VW_DAILY_ROUTE_SUMMARY
        WHERE (:sites IS NULL OR SITE IN (:sites))
        AND (:dateFrom IS NULL OR ROUTEDATE >= :dateFrom)
        AND (:dateTo IS NULL OR ROUTEDATE < DATEADD(day, 1, :dateTo))
    """, nativeQuery = true)
    List<DailyRouteSummary> findBySiteAndDateRange(
            @Param("sites") List<String> sites,
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo);
}
