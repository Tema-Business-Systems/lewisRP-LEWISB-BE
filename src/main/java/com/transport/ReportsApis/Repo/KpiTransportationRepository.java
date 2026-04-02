package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.KpiTransportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiTransportationRepository
        extends JpaRepository<KpiTransportation, String> {
}
