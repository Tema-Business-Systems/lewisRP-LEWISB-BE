package com.transport.ReportsApis.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "XTMSTRIPH")
public class TripHeader {
    @Id
    @Column(name = "tripid")
    private String tripid;
    @Column(name = "site")
    private String site;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "DEPDATE")
    private Date depdate;
    @Column(name = "STARTTIME")
    private String starttime;
    @Column(name = "ARVDATE")
    private Date arvdate;
    @Column(name = "ENDTIME")
    private String endtime;
    @Column(name = "VEHICLE")
    private String vehicle;
    @Column(name = "DRIVER")
    private String driver;
    @Column(name = "LVS")
    private String lvs;
    @Column(name = "VALIDFLG")
    private Integer validflg;
    @Column(name = "TRIPNO")
    private Integer tripno;
    @Column(name = "TOTAL_WEIGHT")
    private BigDecimal totalWeight;
    @Column(name = "ODOMETER_START")
    private Integer odometerStart;
    @Column(name = "ODOMETER_END")
    private Integer odometerEnd;
    @Column(name = "STATUS")
    private Integer status;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripid", referencedColumnName = "tripid")
    private List<TripStop> stops;
}
