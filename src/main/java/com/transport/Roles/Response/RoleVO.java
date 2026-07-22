package com.transport.Roles.Response;

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
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleVO {
    private int updtick;
    private String xrolid;
    private String xrolcode;
    private String xrolname;
    private int xactive;
    private byte[] auuid;
    private Date credattim;
    private Date upddattim;
    private String creusr;
    private String updusr;
    private BigDecimal rowid;
//    private String xdesc;

}
