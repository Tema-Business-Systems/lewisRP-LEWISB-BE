package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.RouteListDocs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteListDocsRepo extends JpaRepository<RouteListDocs, String> {
    List<RouteListDocs> findByTripidIn(List<String> tripIds);
}
