package com.transport.usermanagement.Module.Response;


import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleVO {
    private int updtick;
    private String modId;
    private String modCode;
    private String modName;
    private int isActive;
    private byte[] auuid;
    private Date credattim;
    private Date upddattim;
    private String creusr;
    private String updusr;
    private BigDecimal rowid;
}

