package com.transport.usermanagement.Module.Repository;

import com.transport.Roles.model.Role;
import com.transport.usermanagement.Module.model.Modules;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends CrudRepository<Modules,String> {
    List<Modules> findAll();
    Optional<Modules> findByXmodcode(String xmodcode);

}
