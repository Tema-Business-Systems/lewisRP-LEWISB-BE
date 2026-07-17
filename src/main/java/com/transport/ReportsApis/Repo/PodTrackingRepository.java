package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.PodTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PodTrackingRepository extends JpaRepository<PodTracking, String> {
    List<PodTracking> findBySiteInAndDateBetween(List<String> site, Date dateFrom, Date dateTo);
//    @Query(value = """
//        SELECT *
//        FROM LEWISB.V_POD_TRACKING pod
//        WHERE pod.date BETWEEN :dateForm AND :dateTo
//        """, nativeQuery = true)
//    List <PodTracking> findByDateRange(@Param("dateForm") LocalDate dateForm, @Param("dateTo") LocalDate dateTo );
}
