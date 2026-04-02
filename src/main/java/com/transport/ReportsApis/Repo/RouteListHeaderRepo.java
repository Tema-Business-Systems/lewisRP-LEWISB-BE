package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.RouteListHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RouteListHeaderRepo extends JpaRepository<RouteListHeader, String> {
    List<RouteListHeader> findBySiteInAndTripDateBetween(List<String> site, Date from, Date to);
}
