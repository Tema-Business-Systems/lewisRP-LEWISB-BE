package com.transport.Roles.Controller;


import com.transport.Roles.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transport.Roles.Service.*;
import com.transport.Roles.Response.RoleVO;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("api/roles")
public class RolesController {

 private final RoleService roleService;

    public RolesController(RoleService roleService){
        this.roleService=roleService;

    }


    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleVO role){
       return roleService.createRole(role);
    }


}
