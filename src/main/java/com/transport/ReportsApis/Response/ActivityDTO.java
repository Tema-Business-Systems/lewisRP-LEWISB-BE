package com.transport.ReportsApis.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
    private String type;
    private String start;
    private String end;
    private Integer durationMin;
}
