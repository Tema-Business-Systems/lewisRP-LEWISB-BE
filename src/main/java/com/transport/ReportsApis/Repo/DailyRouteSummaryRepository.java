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
        AND (:date IS NULL 
             OR (ROUTEDATE >= :date 
             AND ROUTEDATE < DATEADD(day, 1, :date)))
    """, nativeQuery = true)
    List<DailyRouteSummary> findBySiteAndDate(@Param("sites") List<String> sites, @Param("date") Date date);
}
