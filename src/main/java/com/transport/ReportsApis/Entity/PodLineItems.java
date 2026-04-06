package com.transport.ReportsApis.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "V_POD_LINE_ITEMS", schema = "LEWISB")
@Getter
@Setter
public class PodLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document")
    private String document;
    @Column(name = "docNum")
    private String docNum;
    @Column(name = "orderNumber")
    private String orderNumber;
    @Column(name = "productId")
    private String productId;
    @Column(name = "description")
    private String description;
    @Column(name = "qtyToPrepare")
    private Integer qtyToPrepare;
    @Column(name = "unit")
    private String unit;
}
