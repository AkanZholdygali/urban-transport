package kz.akan.springcourse.urbantransport.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Stop\"")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Stop {
    @Id
    @Column(name = "stop_id")
    private Integer stopId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "street_id", nullable = false)
    private Street street;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "stops")
    private Set<Route> routes = new HashSet<>();

    @Column(name = "name")
    private String name;
}
