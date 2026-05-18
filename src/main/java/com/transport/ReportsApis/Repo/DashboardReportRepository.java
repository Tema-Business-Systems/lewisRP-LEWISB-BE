package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.DashboardReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface DashboardReportRepository extends JpaRepository<DashboardReport, String>, JpaSpecificationExecutor<DashboardReport> {
}
