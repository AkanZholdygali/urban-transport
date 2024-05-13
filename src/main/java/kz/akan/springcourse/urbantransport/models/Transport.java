package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "Transport", indexes = {
        @Index(name = "IXFK_Transport", columnList = "type_id, route_no")
})
public class Transport {
    @Id
    @Column(name = "license_plate_no", nullable = false, length = 50)
    private String licensePlateNo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("1")
    @JoinColumn(name = "type_id")
    private TransportType type;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "route_no")
    private Route routeNo;

    @Column(name = "num_repairs")
    private Integer numRepairs;

    @ColumnDefault("2020")
    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @ColumnDefault("1")
    @Column(name = "isFunctional")
    private Boolean isFunctional;

}