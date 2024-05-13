package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
public class StreetView {
    @Id
    @Column(name = "street_id", nullable = false)
    private Integer streetId;

    @Column(name = "name", length = 50)
    private String name;

}