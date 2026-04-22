package com.transport.ReportsApis.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "VW_CUSTOMER_SERVICE_LOG", schema = "LEWISB")
@Data
public class CustomerServiceLog {

    @Id
    @Column(name = "ACCT_NO")
    private String acctNo;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "DRIVER")
    private String driver;
    @Column(name = "DRIVERNAME")
    private String driverName;

    @Column(name = "DELIVERY_STATUS")
    private String deliveryStatus;

    @Column(name = "ORDER_CHANGE")
    private String orderChange;

    @Column(name = "ADV_ORDER")
    private String advOrder;

    @Column(name = "PAYMENT")
    private String payment;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SERVICE_DATE")
    private Date serviceDate;

    @Column(name = "SITE")
    private String site;
}
