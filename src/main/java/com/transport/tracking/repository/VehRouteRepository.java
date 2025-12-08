package com.transport.tracking.repository;

import com.transport.tracking.model.VehRoute;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehRouteRepository extends CrudRepository<VehRoute, String> {

    public List<VehRoute> findAll();

    public List<VehRoute> findByFcy(String fcy);

    public List<VehRoute> findByFcyAndDatliv(String fcy, String date);

    public List<VehRoute> findByDatliv(String datliv);

    public VehRoute findByXnumpc(String vrcode);
}
