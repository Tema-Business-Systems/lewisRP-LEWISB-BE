package com.transport.usermanagement.rolemodule.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleInfo {
    private String moduleId;
    private String moduleCode;
    private String moduleName;
    private int isActive;
}
