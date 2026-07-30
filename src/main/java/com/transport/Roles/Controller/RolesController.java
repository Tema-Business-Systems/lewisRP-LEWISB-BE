package com.transport.Roles.Controller;


import com.transport.Roles.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.transport.Roles.Service.*;
import com.transport.Roles.Response.RoleVO;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
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
        HashMap<String,Object> hs=  new HashMap<>();
        try {
            roleService.deleteRole(roleId);
            hs.put("message","Role deleted successfully");
            hs.put("status", HttpStatus.OK.value());
            hs.put("data",null);
            return new ResponseEntity<>(hs,HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            hs.put("message","Role not found with id :" +roleId);
            hs.put("status", HttpStatus.NOT_FOUND.value());
            hs.put("data",null);
            return new ResponseEntity<>(hs,HttpStatus.BAD_REQUEST);

        }


    }

    @PutMapping("/updateRole/{roleId}")
    public ResponseEntity<?> updationRole(@PathVariable String roleId, @RequestBody RoleVO roleVO) {
        HashMap<String,Object> hs=  new HashMap<>();
        try {
           return roleService.updateRole(roleId, roleVO);
        } catch (RuntimeException e) {
            hs.put("message",e.getMessage());
            hs.put("status", HttpStatus.NOT_FOUND.value());
            hs.put("data",null);
            return new ResponseEntity<>(hs,HttpStatus.NOT_FOUND);
        }
    }


}