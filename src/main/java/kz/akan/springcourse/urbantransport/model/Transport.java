package kz.akan.springcourse.urbantransport.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Transport\"")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Transport {
    @Id
    @Column(name = "license_plate_no")
    private String licensePlateNo;


    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TransportType type;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "transports")
    private Set<Route> routes = new HashSet<>();

    @Column(name = "num_repairs")
    private Integer numRepairs;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

}
