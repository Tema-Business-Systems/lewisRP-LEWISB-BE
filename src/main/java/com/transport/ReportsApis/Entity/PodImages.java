package com.transport.ReportsApis.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "V_POD_IMAGES", schema = "LEWISB")
@Getter
@Setter
public class PodImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document")
    private String document;
    @Column(name = "type")
    private String type;
    @Column(name = "contentUrl")
    private Integer contentUrl;
    @Column(name = "postDate")
    private Integer postDate;
}
