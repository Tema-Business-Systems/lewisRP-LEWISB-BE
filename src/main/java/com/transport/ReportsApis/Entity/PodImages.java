package com.transport.ReportsApis.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "V_POD_IMAGES", schema = "LEWISB")
@IdClass(PodImagesId.class)
@Getter
@Setter
public class PodImages {
    @Id
    @Column(name = "document")
    private String document;
    @Column(name = "type")
    private String type;
    @Id
    @Column(name = "contentUrl")
    private Integer contentUrl;
    @Column(name = "postDate")
    private Integer postDate;
}
