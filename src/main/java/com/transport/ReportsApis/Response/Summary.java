package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class Summary {
    private CustomersServiced customersServiced;
    private TotalSales totalSales;
    private TotalSales collections;
}


