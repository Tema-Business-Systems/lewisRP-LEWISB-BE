package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DriverActivitySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriverActivitySummaryRepository
        extends JpaRepository<DriverActivitySummary, Date> {

    @Query(value = """
            SELECT *
            FROM LEWISB.VW_DRIVER_ACTIVITY_SUMMARY
            WHERE (:dateFrom IS NULL OR CAST(ACTIVITY_DATE AS DATE) >= :dateFrom)
            AND (:dateTo IS NULL OR CAST(ACTIVITY_DATE AS DATE) <= :dateTo)
            """, nativeQuery = true)
    List<DriverActivitySummary> getByDateRange(
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo);
}
