package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TripStopId implements Serializable {

    @Column(name = "tripid")
    private String tripid;

    @Column(name = "line_no")
    private Integer lineNo;
}
