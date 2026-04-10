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
            WHERE (:date IS NULL OR CAST(ACTIVITY_DATE AS DATE) = :date)
            """, nativeQuery = true)
    List<DriverActivitySummary> getByDate(@Param("date") Date date);
}