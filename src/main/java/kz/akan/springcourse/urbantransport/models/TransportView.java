package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
public class TransportView {
    @Id
    @Column(name = "license_plate_no", nullable = false, length = 50)
    private String licensePlateNo;

    @Column(name = "type_id", columnDefinition = "tinyint")
    private Short typeId;

    @Column(name = "route_no", length = 50)
    private String routeNo;

    @Column(name = "num_repairs")
    private Integer numRepairs;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Column(name = "isFunctional")
    private Boolean isFunctional;

}