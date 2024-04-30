package kz.akan.springcourse.urbantransport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "\"Street\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Street {
    @Id
    @Column(name = "street_id")
    private Integer streetId;

    @Column(name = "name")
    private String name;
}
