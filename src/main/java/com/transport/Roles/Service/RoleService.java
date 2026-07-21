package com.transport.Roles.Service;


import com.transport.Roles.Repository.RoleRepository;
import com.transport.Roles.Response.RoleVO;
import com.transport.Roles.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class RoleService {

    private final RoleRepository roleRepo;

    private RoleService(RoleRepository roleRepo){
        this.roleRepo = roleRepo;
    }

    public ResponseEntity<?> createRole(RoleVO role){

        if(role.getXrolcode() != null && role.getXactive() != null){
      Role r = new Role();
//      UUID id = UUID.randomUUID();
//      r.setXrolid(id);
      r.setXrolcode(role.getXrolcode());
      r.setActive(role.getXactive());
      r.setCreusr(role.getCreusr());
      r.setUpdusr(role.getUpdusr());
      r.setCredattim(role.getCredattim());
       r.setUpddattim(role.getUpddattim());
            Role savedRole= roleRepo.save(r);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
        }else {

            return new ResponseEntity<>("xrolcode and active are required.", HttpStatus.BAD_REQUEST);

        }

    }
}
