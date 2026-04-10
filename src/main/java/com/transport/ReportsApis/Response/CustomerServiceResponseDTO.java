package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;

@Data
public class CustomerServiceResponseDTO {
    private SummaryDataDTO summary;
    private FiltersDTO filters;
    private List<CustomerServiceDetailsDTO> customerServiceDetails;
}
