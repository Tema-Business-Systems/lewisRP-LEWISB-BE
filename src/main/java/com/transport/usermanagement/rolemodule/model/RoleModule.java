package com.transport.usermanagement.rolemodule.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "XX10CROLEMOD", schema = "LEWISB")
public class RoleModule {
    @Column(name= "UPDTICK_0")
    private int updtick;
    @Id
    @Column(name="XROLEMODID_0", nullable = false)
    private String xroleModId;
    @Column(name="XROLEID_0",nullable=false)
    private String xroleId;
    @Column(name="XROLECODE_0",unique=true, nullable=false)
    private String xroleCode;
    @Column(name="XMODULEID_0",nullable=false)
    private String xmoduleId;
    @Column(name="XMODULECODE_0",unique=true, nullable=false)
    private String xmoduleCode;
    @Column(name="XMODULENAME_0",unique=true,nullable=false)
    private String xmoduleName;
    @Column(name="XMODISACTIVE_0",nullable=false)
    private int xmodIsactive;
    @Column(name= "AUUID_0")
    private byte[] auuid;
    @Column(name= "CREDATTIM_0")
    private Date credattim;
    @Column(name= "UPDDATTIM_0")
    private Date upddattim;
    @Column(name= "CREUSR_0")
    private String creusr;
    @Column(name= "UPDUSR_0")
    private String updusr;
    @Column(name= "ROWID", insertable = false, updatable = false)
    private BigDecimal rowid;


}
