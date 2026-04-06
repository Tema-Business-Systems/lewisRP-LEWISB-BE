package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.PodLineItems;
import com.transport.ReportsApis.Entity.PodLineItemsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodLineItemsRepository extends JpaRepository<PodLineItems, PodLineItemsId> {
    List<PodLineItems> findByDocument(String document);
}
