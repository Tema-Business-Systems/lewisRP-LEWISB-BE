package com.transport.ReportsApis.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
@Table(name = "V_ROUTE_LIST_DOCS", schema = "LEWISB")
public class RouteListDocs {
    @Id
    @Column(name = "tripid")
    private String tripid;
    @Column(name = "sequence")
    private Integer sequence;
    @Column(name = "documentNo")
    private String documentNo;
    @Column(name = "docType")
    private String docType;
    @Column(name = "arvTime")
    private String arvTime;
    @Column(name = "depTime")
    private String depTime;
    @Column(name = "status")
    private String status;
}