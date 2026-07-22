package com.transport.Roles.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

    @Getter
    @Setter
    @Entity
    @Table(name = "XX10CROL", schema = "LEWISB")
    public class Role {

        @Column(name= "UPDTICK_0")
        private int updtick;
        @Id
//        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name= "XROLID_0", nullable = false)
        private String xrolid;
        @Column(name="XROLCODE_0", unique=true, nullable=false)
        private String xrolcode;
        @Column(name="XROLNAME_0")
        private String xrolname;
        @Column(name="XROLACTIVE_0")
        private boolean active;
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


//      @Column(name= "AUUID_0")
//      private byte[] auuid;
//
//      @Column(name="XROLISACTIVE")
//      private String isActive;
//
//        @Column(name= "XDESC_0")
//        private String xdesc;

      /*@ManyToMany(mappedBy = "roles")
      private Set<User> users = new HashSet<>();*/
    }


