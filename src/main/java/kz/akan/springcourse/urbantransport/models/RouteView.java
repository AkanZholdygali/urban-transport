package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalTime;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
public class RouteView {
    @Id
    @Column(name = "route_no", nullable = false, length = 50)
    private String routeNo;

    @Column(name = "type_id", columnDefinition = "tinyint")
    private Short typeId;

    @Column(name = "first_trip")
    private LocalTime firstTrip;

    @Column(name = "last_trip")
    private LocalTime lastTrip;

    @Column(name = "interval_mins")
    private Integer intervalMins;

    @Column(name = "passengers_day")
    private Double passengersDay;

}