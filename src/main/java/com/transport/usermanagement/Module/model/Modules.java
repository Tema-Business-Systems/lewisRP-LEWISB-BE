package com.transport.usermanagement.Module.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "XX10CMODULES", schema = "LEWISB")
public class Modules {
    @Column(name= "UPDTICK_0")
    private int updtick;
    @Id
    @Column(name= "XMODLID_0", nullable = false)
    private String xmodid;
    @Column(name="XMODCODE_0", unique=true, nullable=false)
    private String xmodcode;
    @Column(name="XMODNAME_0")
    private String xmodname;
    @Column(name="XMODACTIV_0")
    private int xisactive;
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
