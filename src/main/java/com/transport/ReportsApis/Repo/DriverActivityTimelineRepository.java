package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DriverActivityTimeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriverActivityTimelineRepository
        extends JpaRepository<DriverActivityTimeline, String> {

    @Query(value = """
            SELECT *
            FROM LEWISB.VW_DRIVER_ACTIVITY_TIMELINE
            WHERE (:site IS NULL OR SITE IN (:site))
            AND (:dateFrom IS NULL OR CAST(ACTIVITY_DATE AS DATE) >= :dateFrom)
            AND (:dateTo IS NULL OR CAST(ACTIVITY_DATE AS DATE) <= :dateTo)
            ORDER BY DRIVER, START_TIME
            """, nativeQuery = true)
    List<DriverActivityTimeline> getBySiteAndDateRange(
            @Param("site") List<String> site,
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo);
}
