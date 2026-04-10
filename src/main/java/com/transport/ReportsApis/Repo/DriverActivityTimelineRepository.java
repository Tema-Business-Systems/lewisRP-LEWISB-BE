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
            AND (:date IS NULL OR CAST(ACTIVITY_DATE AS DATE) = :date)
            ORDER BY DRIVER, START_TIME
            """, nativeQuery = true)
    List<DriverActivityTimeline> getBySiteAndDate(
            @Param("site") List<String> site,
            @Param("date") Date date);
}
