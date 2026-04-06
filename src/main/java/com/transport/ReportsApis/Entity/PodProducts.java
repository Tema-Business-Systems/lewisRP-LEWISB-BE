package com.transport.ReportsApis.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "V_POD_PRODUCTS", schema = "LEWISB")
@Getter
@Setter
public class PodProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "deliveryNum")
    private String deliveryNum;
    @Column(name = "product")
    private String product;
    @Column(name = "orderedQty")
    private Integer orderedQty;
    @Column(name = "deliveredQty")
    private Integer deliveredQty;
    @Column(name = "unit")
    private String unit;
    @Column(name = "packages")
    private Integer packages;
    @Column(name = "mass")
    private Integer mass;
    @Column(name = "massUnit")
    private String massUnit;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "volumeUnit")
    private String volumeUnit;
    @Column(name = "leftQtyToShip")
    private Integer leftQtyToShip;
}
