package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.OrderCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCalendarRepository extends JpaRepository<OrderCalendar, String> {
}
