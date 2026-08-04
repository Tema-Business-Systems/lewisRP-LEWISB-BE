package com.transport.usermanagement.rolemodule.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleModuleVO {
    private int updtick;
    private String rolModId;
    private String rolCode;
    private String rolId;
    private List<ModuleInfo> moduleList;
    private byte[] auuid;
    private Date credattim;
    private Date upddattim;
    private String creusr;
    private String updusr;
    private BigDecimal rowid;
}
