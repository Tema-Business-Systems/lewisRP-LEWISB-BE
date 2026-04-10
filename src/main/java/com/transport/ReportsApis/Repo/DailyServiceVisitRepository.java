package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DailyServiceVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyServiceVisitRepository extends JpaRepository<DailyServiceVisit, String> {
}
