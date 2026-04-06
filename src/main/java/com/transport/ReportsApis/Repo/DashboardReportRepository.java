package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DashboardReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardReportRepository extends JpaRepository<DashboardReport, String> {

}
