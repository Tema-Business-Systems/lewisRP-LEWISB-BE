package com.transport.ReportsApis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class KpiTransportationId implements Serializable {
    @Column(name = "site")
    private String site;
    @Column(name = "period")
    private String period;
}
