package com.transport.ReportsApis.Response;

import lombok.Data;

@Data
public class SummaryDataDTO {
    private SummaryItemDTO customersServiced;
    private SummaryItemDTO cashAccounts;
    private SummaryItemDTO chargeAccounts;
    private SummaryItemDTO skippedMissed;
}
