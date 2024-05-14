package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;

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

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("1")
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

}