package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DailyServiceVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyServiceVisitRepository extends JpaRepository<DailyServiceVisit, String> {
    @Query(value = """
            SELECT *
            FROM LEWISB.VW_DAILY_SERVICE_VISIT
            WHERE (:site IS NULL OR SITE IN (:site))
            AND (:dateFrom IS NULL OR CAST(VISIT_DATE AS DATE) >= :dateFrom)
            AND (:dateTo IS NULL OR CAST(VISIT_DATE AS DATE) <= :dateTo)
            """, nativeQuery = true)
    List<DailyServiceVisit> findBySiteAndVisitDateRange(
            @Param("site") List<String> site,
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo);
}
