package kz.akan.springcourse.urbantransport.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Route\"")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Route {
    @Id
    @Column(name = "route_no")
    private String routeNo;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TransportType type;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "\"TransportOnRoute\"",
            joinColumns = { @JoinColumn(name = "route_no") },
            inverseJoinColumns = { @JoinColumn(name = "license_plate_no") })
    private Set<Transport> transports = new HashSet<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "\"StopOnRoute\"",
            joinColumns = { @JoinColumn(name = "route_no") },
            inverseJoinColumns = { @JoinColumn(name = "stop_id") })
    private Set<Stop> stops = new HashSet<>();


    @Column(name = "first_trip")
    private Time firstTrip;

    @Column(name = "last_trip")
    private String lastTrip;

    @Column(name = "interval_mins")
    private Integer intervalMins;

    @Column(name = "passengers_day")
    private Integer passengersDay;
}
