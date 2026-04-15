package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.OrderCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderCalendarRepository extends JpaRepository<OrderCalendar, String> {
    List<OrderCalendar> findByOrderDateBetween(LocalDate from, LocalDate to);
}
