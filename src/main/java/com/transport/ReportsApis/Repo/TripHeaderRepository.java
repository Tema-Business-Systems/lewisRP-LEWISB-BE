package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.TripHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TripHeaderRepository extends JpaRepository<TripHeader, String> {
    List<TripHeader> findBySiteInAndDate(List<String> site, Date date);}
