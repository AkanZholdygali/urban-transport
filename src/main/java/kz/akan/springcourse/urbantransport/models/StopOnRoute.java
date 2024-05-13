package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "StopOnRoute", indexes = {
        @Index(name = "IXFK_StopOnRoute", columnList = "route_no, stop_order")
})
public class StopOnRoute {
    @EmbeddedId
    private StopOnRouteId id;

    @MapsId("routeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_no", nullable = false)
    private Route routeNo;

    @MapsId("stopId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;

}