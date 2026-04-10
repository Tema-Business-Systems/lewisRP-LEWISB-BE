package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.CustomerServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceRepository
        extends JpaRepository<CustomerServiceLog, String> {
}
