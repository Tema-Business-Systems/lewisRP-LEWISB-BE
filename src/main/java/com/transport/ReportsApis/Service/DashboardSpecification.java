package com.transport.ReportsApis.Service;


import com.transport.ReportsApis.Entity.DashboardReport;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class DashboardSpecification {

    public static Specification<DashboardReport> filter(
            List<String> sites,
            Date date,
            Date dateFrom,
            Date dateTo) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // ✅ SITE FILTER
            if (sites != null && !sites.isEmpty()) {
                predicates.add(root.get("site").in(sites));
            }

            // ❗ IMPORTANT FIX:
            // Your view uses GETDATE() for some datasets
            // so strict date filter breaks routes & locations

            if (date != null) {
                predicates.add(
                        cb.equal(root.get("reportDate"), date)
                );
            }

            // (optional range)
            if (dateFrom != null && dateTo != null) {
                predicates.add(
                        cb.between(root.get("reportDate"), dateFrom, dateTo)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
