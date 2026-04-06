package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.PodTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PodTrackingRepository extends JpaRepository<PodTracking, String> {
    List<PodTracking> findBySiteInAndDateBetween(List<String> site, Date dateFrom, Date dateTo);
}
