package com.transport.ReportsApis.Repo;

import com.transport.ReportsApis.Entity.PodImages;
import com.transport.ReportsApis.Entity.PodImagesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodImagesRepository extends JpaRepository<PodImages, PodImagesId> {
    List<PodImages> findByDocument(String document);
}
