package com.transport.Roles.Service;


import com.transport.Roles.Repository.RoleRepository;
import com.transport.Roles.Response.RoleVO;
import com.transport.Roles.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RoleService {

    private final RoleRepository roleRepo;

    private RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }


    private byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    public ResponseEntity<?> createRole(RoleVO role) {

        if (role.getXrolcode() != null ) {
            Role r = new Role();
            r.setXrolid(UUID.randomUUID().toString());
            r.setUpdtick(1);
            r.setXrolcode(role.getXrolcode());
            r.setXrolname(role.getXrolname());
            r.setXactive(role.getXactive());
            r.setAuuid(uuidToBytes(UUID.randomUUID()));
            r.setCreusr(role.getCreusr());
            r.setUpdusr(role.getUpdusr());
            r.setCredattim(role.getCredattim());
            r.setUpddattim(role.getUpddattim());
            Role savedRole = roleRepo.save(r);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created Role");
        } else {

            return new ResponseEntity<>("xrolcode are required.", HttpStatus.BAD_REQUEST);

        }

    }


    public void deleteRole(String roleId) {
        if(!roleRepo.existsById(roleId)){
            throw new RuntimeException("Role not found with id : " + roleId);
        }
            roleRepo.deleteById(roleId);
    }



    public List<RoleVO> getAllRoles(){

        return roleRepo.findAll()
                .stream()
                .map(r -> {
                    RoleVO vo = new RoleVO();
                    vo.setXrolid(r.getXrolid());
                    vo.setXrolcode(r.getXrolcode());
                    vo.setXrolname(r.getXrolname());
                    vo.setXactive(r.getXactive());
//                    vo.setXactive(r.isXactive());
//                    vo.setXactive(Integer.valueOf(1).equals(r.get));
                    vo.setUpdtick(r.getUpdtick());
                    vo.setCreusr(r.getCreusr());
                    vo.setUpdusr(r.getUpdusr());
                    vo.setCredattim(r.getCredattim());
                    vo.setUpddattim(r.getUpddattim());
                    return vo;

                })
                .collect(Collectors.toList());
    }



    public ResponseEntity<?> updateRole(String roleId, RoleVO roleVO) {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id : " + roleId));
        role.setXrolcode(roleVO.getXrolcode());
        role.setXrolname(roleVO.getXrolname());
        role.setXactive(roleVO.getXactive());
        role.setUpdusr(roleVO.getUpdusr());
        role.setUpddattim(new Timestamp(System.currentTimeMillis()));
        if (role.getUpdtick() == 0) {
            role.setUpdtick(1);
        } else {
            role.setUpdtick(role.getUpdtick() + 1);
        }

       Role updatedRole = roleRepo.save(role);
        RoleVO response = new RoleVO();
        response.setXrolid(updatedRole.getXrolid());
        response.setXrolcode(updatedRole.getXrolcode());
        response.setXrolname(updatedRole.getXrolname());
//        response.setXactive(Integer.valueOf(1).equals(updatedRole.isXactive()));
//        response.setXactive(Integer.valueOf(1).equals(updatedRole.get));
        response.setXactive(updatedRole.getXactive());
        response.setUpdtick(updatedRole.getUpdtick());
        response.setCreusr(updatedRole.getCreusr());
        response.setUpdusr(updatedRole.getUpdusr());
        response.setCredattim(updatedRole.getCredattim());
        response.setUpddattim(updatedRole.getUpddattim());
        return ResponseEntity.ok(response);
    }





}
