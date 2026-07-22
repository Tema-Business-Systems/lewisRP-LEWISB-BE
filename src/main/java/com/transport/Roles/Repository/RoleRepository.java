package com.transport.Roles.Repository;

import com.transport.Roles.model.Role;
import com.transport.tracking.model.ChangeLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository  extends CrudRepository<Role,String> {
    List <Role> findAll();

}
