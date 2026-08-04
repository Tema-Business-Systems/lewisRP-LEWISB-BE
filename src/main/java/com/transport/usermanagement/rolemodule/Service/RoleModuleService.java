package com.transport.usermanagement.rolemodule.Service;

import com.transport.Payload.ApiResponse;
import com.transport.Roles.Repository.RoleRepository;
import com.transport.Roles.Response.RoleVO;
import com.transport.Roles.Service.RoleService;
import com.transport.Roles.model.Role;
import com.transport.usermanagement.Module.Response.ModuleVO;
import com.transport.usermanagement.Module.Service.ModuleService;
import com.transport.usermanagement.rolemodule.Repository.RoleModuleRepository;
import com.transport.usermanagement.rolemodule.Response.ModuleInfo;
import com.transport.usermanagement.rolemodule.Response.RoleModuleVO;
import com.transport.usermanagement.rolemodule.model.RoleModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.util.*;

@Service
public class RoleModuleService {

    private final RoleModuleRepository roleModRepo;
    private ModuleService modService;
    private RoleRepository roleRepo;


    public RoleModuleService(RoleModuleRepository roleModRepo, ModuleService modService, RoleRepository roleRepo) {
        this.roleModRepo = roleModRepo;
        this.modService = modService;
        this.roleRepo = roleRepo;
    }


    private byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    @Transactional
    public ResponseEntity<?> assignModulesToRole(RoleModuleVO rolModObj) {

        roleModRepo.deleteByXroleId(rolModObj.getRolId());

        List<RoleModule> addRoleModules = new ArrayList<>();

        for (ModuleInfo modInfo : rolModObj.getModuleList()) {
            RoleModule rm = new RoleModule();
            rm.setXroleModId(UUID.randomUUID().toString());
            rm.setXroleId(rolModObj.getRolId());
            rm.setXroleCode(rolModObj.getRolCode());
            rm.setXmoduleId(modInfo.getModuleId());
            rm.setXmoduleCode(modInfo.getModuleCode());
            rm.setXmoduleName(modInfo.getModuleName());
            rm.setAuuid(uuidToBytes(UUID.randomUUID()));
            rm.setXmodIsactive(modInfo.getIsActive());
            rm.setCreusr(rolModObj.getCreusr());
            rm.setUpdusr(rolModObj.getUpdusr());
            rm.setCredattim(rolModObj.getCredattim());
            rm.setUpddattim(rolModObj.getUpddattim());
            addRoleModules.add(rm);
        }

        List<RoleModule> savedList = (List<RoleModule>) roleModRepo.saveAll(addRoleModules);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Role Module created successfully.",
                        null
                ));
    }

//    public List<RoleModule> getRoleModules(String roleId){
//       return  roleModRepo.findByxroleId(roleId);
//    }


    public RoleModuleVO getRoleModules(String roleId) {

        List<ModuleVO> modules = modService.getAllModules();
        List<RoleModule> roleModules = roleModRepo.findByXroleId(roleId);

        Optional<Role> existingRole = roleRepo.findByXrolid(roleId);

        if (existingRole.isEmpty()) {
            return new RoleModuleVO();
        }

        RoleModuleVO response;

        if (!roleModules.isEmpty()) {
            response = buildRoleResponse(roleModules.get(0));
        } else {
            response = new RoleModuleVO();

            Role role = existingRole.get();
            response.setRolId(role.getXrolid());
            response.setRolCode(role.getXrolcode());
            response.setCredattim(new Date());
            response.setUpddattim(new Date());
        }

        Map<String, ModuleInfo> moduleMap = buildModuleMap(modules);

        populateActiveModules(roleModules, moduleMap);

        response.setModuleList(new ArrayList<>(moduleMap.values()));

        return response;
    }


    private RoleModuleVO buildRoleResponse(RoleModule roleModule) {

        RoleModuleVO response = new RoleModuleVO();

        response.setRolId(roleModule.getXroleId());
        response.setRolCode(roleModule.getXroleCode());
        response.setCreusr(roleModule.getCreusr());
        response.setUpdusr(roleModule.getUpdusr());
        response.setCredattim(roleModule.getCredattim());
        response.setUpddattim(roleModule.getUpddattim());

        return response;
    }


    private Map<String, ModuleInfo> buildModuleMap(List<ModuleVO> modules) {

        Map<String, ModuleInfo> moduleMap = new LinkedHashMap<>();

        for (ModuleVO module : modules) {

            ModuleInfo moduleInfo = new ModuleInfo();

            moduleInfo.setModuleId(module.getModId());
            moduleInfo.setModuleCode(module.getModCode());
            moduleInfo.setModuleName(module.getModName());
            moduleInfo.setIsActive(module.getIsActive());

            moduleMap.put(module.getModId(), moduleInfo);
        }

        return moduleMap;
    }


    private void populateActiveModules(List<RoleModule> roleModules,
                                       Map<String, ModuleInfo> moduleMap) {

        if (roleModules.isEmpty()) {
            moduleMap.values()
                    .forEach(moduleInfo -> moduleInfo.setIsActive(0));
            return;
        }

        for (RoleModule roleModule : roleModules) {

            ModuleInfo moduleInfo = moduleMap.get(roleModule.getXmoduleId());

            if (moduleInfo != null) {
                moduleInfo.setIsActive(roleModule.getXmodIsactive());
            }
        }
    }

}
