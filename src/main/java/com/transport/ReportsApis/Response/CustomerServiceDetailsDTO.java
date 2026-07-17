package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class CustomerServiceDetailsDTO {
    private String accountNo;
    private String customerNo;
    private String customer;
    private String storeNo;
    private String accountType;
    private String driver;
    private String driverName;
    private String delivery;
    private String orderChange;
    private String advanceOrder;
    private String payment;
    private String status;
}
