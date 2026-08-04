package com.transport.usermanagement.rolemodule.Controller;

import com.transport.Payload.ApiResponse;
import com.transport.usermanagement.Module.Response.ModuleVO;
import com.transport.usermanagement.rolemodule.Response.ModuleInfo;
import com.transport.usermanagement.rolemodule.Response.RoleModuleVO;
import com.transport.usermanagement.rolemodule.Service.RoleModuleService;
import com.transport.usermanagement.rolemodule.model.RoleModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role-modules")
public class RoleModuleController {

    private final RoleModuleService rolModService;

    RoleModuleController(RoleModuleService rolModService) {
        this.rolModService = rolModService;
    }

    @PostMapping("/{roleId}")
    public ResponseEntity<?> assignModulesToRole(
            @PathVariable String roleId,
            @RequestBody RoleModuleVO rolModObj) {

        try {

            if (roleId == null || rolModObj == null || rolModObj.getRolCode() == null) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse<>(
                                HttpStatus.BAD_REQUEST.value(),
                                "No Data Found.",
                                null
                        )
                );
            }

            if (rolModObj.getModuleList() != null) {
                for (ModuleInfo module : rolModObj.getModuleList()) {
//                    System.out.println(
//                            "Module ID : " + module.getModuleId()
//                                    + ", Module Code : " + module.getModuleCode()
//                                    + ", Active : " + module.getIsActive()
//                    );
                }

                rolModService.assignModulesToRole(rolModObj);

            }
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            HttpStatus.CREATED.value(),
                            "Roles - Modules assigned successfully.",
                            null
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            e.getMessage(),
                            null
                    )
            );
        }
    }


    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleModules(@PathVariable String roleId) {
        try {
//            List<RoleModuleVO> rolModList = rolModService.getRoleModules(roleId);
            RoleModuleVO rolModInfo = rolModService.getRoleModules(roleId);
            if (rolModInfo == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(
                                HttpStatus.BAD_REQUEST.value(),
                                "No Data Found.",
                                null
                        ));
            }


            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(
                            HttpStatus.OK.value(),
                            "successfully retrive Role-Modules List.",
                            rolModInfo
                    ));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(
                            HttpStatus.BAD_REQUEST.value(),
                            "No Data Found.",
                            null
                    ));
        }

    }


}
