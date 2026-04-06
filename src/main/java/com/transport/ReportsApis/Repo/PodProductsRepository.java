package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.PodProducts;
import com.transport.ReportsApis.Entity.PodProductsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodProductsRepository extends JpaRepository<PodProducts, PodProductsId> {
    List<PodProducts> findByDeliveryNum(String deliveryNum);
}
