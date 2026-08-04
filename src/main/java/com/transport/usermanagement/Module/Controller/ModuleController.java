package com.transport.usermanagement.Module.Controller;

import com.transport.Payload.ApiResponse;
import com.transport.Roles.Response.RoleVO;
import com.transport.Roles.Service.RoleService;
import com.transport.usermanagement.Module.Response.ModuleVO;
import com.transport.usermanagement.Module.Service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody ModuleVO moduleObj) {
        return moduleService.createModule(moduleObj);
    }

    @GetMapping("/getModules")
    public  ResponseEntity<?> getRoles() {
        try {
            List<ModuleVO> modules = moduleService.getAllModules();

            if (modules.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(
                                HttpStatus.BAD_REQUEST.value(),
                                "No Data Found.",
                                null
                        ));
            }

              return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            HttpStatus.CREATED.value(),
                            "Module created successfully.",
                            modules
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
