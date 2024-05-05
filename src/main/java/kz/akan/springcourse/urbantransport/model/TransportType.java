package kz.akan.springcourse.urbantransport.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "\"TransportType\"")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportType {
    @Id
    @Column(name = "type_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "fare")
    private BigDecimal fare;

    @Column(name = "avg_speed")
    private Double avgSpeed;

    @Column(name = "cars_at_park")
    private Integer carsAtPark;

    @Column(name = "max_passengers")
    private Integer maxPassengers;

}
