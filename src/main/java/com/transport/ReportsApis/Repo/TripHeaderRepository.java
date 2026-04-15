package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.TripHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TripHeaderRepository extends JpaRepository<TripHeader, String> {
    @Query(value = """
            SELECT *
            FROM XTMSTRIPH
            WHERE (:site IS NULL OR SITE IN (:site))
            AND (:dateFrom IS NULL OR DATE >= :dateFrom)
            AND (:dateTo IS NULL OR DATE < DATEADD(day, 1, :dateTo))
            """, nativeQuery = true)
    List<TripHeader> findBySiteAndDateRange(
            @Param("site") List<String> site,
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo);
}
