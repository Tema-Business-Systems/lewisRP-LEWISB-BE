package com.transport.ReportsApis.Response;

import lombok.Data;
import java.util.List;

@Data
public class FiltersDTO {

    private List<String> drivers;
    private List<String> accountTypes;
}
