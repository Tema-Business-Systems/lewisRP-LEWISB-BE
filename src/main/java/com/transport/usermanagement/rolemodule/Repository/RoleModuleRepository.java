package com.transport.usermanagement.rolemodule.Repository;

import com.transport.usermanagement.Module.model.Modules;
import com.transport.usermanagement.rolemodule.Response.RoleModuleVO;
import com.transport.usermanagement.rolemodule.model.RoleModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleModuleRepository extends CrudRepository<RoleModule,String> {
//    List<RoleModule> findByxroleId(String roleId);
    void deleteByXroleId(String roleId);
//    List<RoleModule> saveAll();
    List<RoleModule> findByXroleId(String roleId);
}
