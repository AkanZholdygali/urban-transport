package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Route", indexes = {
        @Index(name = "IXFK_Route", columnList = "type_id, route_no")
})
public class Route {
    @Id
    @Column(name = "route_no", nullable = false, length = 50)
    private String routeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TransportType type;

    @Column(name = "first_trip")
    private LocalTime firstTrip;

    @Column(name = "last_trip")
    private LocalTime lastTrip;

    @ColumnDefault("10")
    @Column(name = "interval_mins")
    private Integer intervalMins;

    @Column(name = "passengers_day")
    private Double passengersDay;

    @OneToMany(mappedBy = "routeNo")
    private Set<StopOnRoute> stopOnRoutes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "routeNo")
    private Set<Transport> transports = new LinkedHashSet<>();

}