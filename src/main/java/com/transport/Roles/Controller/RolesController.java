package com.transport.Roles.Controller;


import com.transport.Roles.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.transport.Roles.Service.*;
import com.transport.Roles.Response.RoleVO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/roles")
public class RolesController {

    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;

    }

    @GetMapping("/getRoles")
    public List<RoleVO> getRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleVO role) {
        return roleService.createRole(role);
    }


    @DeleteMapping("/deleteRole/{roleId}")
    public ResponseEntity<?> deletionRole(@PathVariable String roleId) {
        try {
            roleService.deleteRole(roleId);
            return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/updateRole/{roleId}")
    public ResponseEntity<?> updationRole(@PathVariable String roleId, @RequestBody RoleVO roleVO) {
        try {
           return roleService.updateRole(roleId, roleVO);
//            return new ResponseEntity<>("Role updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}