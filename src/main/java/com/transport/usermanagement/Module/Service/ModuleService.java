package com.transport.usermanagement.Module.Service;

import com.transport.Payload.ApiResponse;
import com.transport.Roles.Repository.RoleRepository;
import com.transport.Roles.Response.RoleVO;
import com.transport.Roles.model.Role;
import com.transport.usermanagement.Module.Repository.ModuleRepository;
import com.transport.usermanagement.Module.Response.ModuleVO;
import com.transport.usermanagement.Module.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepo;

    private ModuleService(ModuleRepository moduleRepo) {
        this.moduleRepo = moduleRepo;
    }

    private byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }


    public  ResponseEntity<?> createModule(ModuleVO moduleObj){
        HashMap<String,Object> hs=  new HashMap<>();
        if (moduleObj.getModCode() == null || moduleObj.getModCode().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "Module code is required.",
                            null
                    ));
        }
        Optional<Modules> existingModuleCode = moduleRepo.findByXmodcode(moduleObj.getModCode());
        if (existingModuleCode.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(
                            HttpStatus.CONFLICT.value(),
                            "Module code already exists.",
                            null
                    ));
        }

        if (moduleObj.getModCode() != null ) {
            Modules m = new Modules();
            m.setXmodid(UUID.randomUUID().toString());
            m.setUpdtick(moduleObj.getUpdtick());
            m.setXmodcode(moduleObj.getModCode());
            m.setXmodname(moduleObj.getModName());
            m.setXisactive(moduleObj.getIsActive());
            m.setAuuid(uuidToBytes(UUID.randomUUID()));
            m.setCreusr(moduleObj.getCreusr());
            m.setUpdusr(moduleObj.getUpdusr());
            m.setCredattim(moduleObj.getCredattim());
            m.setUpddattim(moduleObj.getUpddattim());

            Modules savedModule = moduleRepo.save(m);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            HttpStatus.CREATED.value(),
                            "Module created successfully.",
                            savedModule
                    ));
        } else {
           return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "Module code is required.",
                            null
                    ));
        }

    }

    public List<ModuleVO> getAllModules(){
        return moduleRepo.findAll()
                .stream()
                .map(m -> {
                    ModuleVO vo = new ModuleVO();
                    vo.setModId(m.getXmodid());
                    vo.setModCode(m.getXmodcode());
                    vo.setModName(m.getXmodname());
                    vo.setIsActive(m.getXisactive());
                    vo.setUpdtick(m.getUpdtick());
                    vo.setCreusr(m.getCreusr());
                    vo.setUpdusr(m.getUpdusr());
                    vo.setCredattim(m.getCredattim());
                    vo.setUpddattim(m.getUpddattim());
                    return vo;
                })
                .collect(Collectors.toList());
    }

}
