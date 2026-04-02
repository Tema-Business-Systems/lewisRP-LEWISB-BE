package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.KpiTransportationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiTransportationDetailRepository
        extends JpaRepository<KpiTransportationDetail, String> {
}
