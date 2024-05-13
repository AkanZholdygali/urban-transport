package kz.akan.springcourse.urbantransport.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TransportType {
    @Id
    @Column(name = "type_id", columnDefinition = "tinyint not null")
    private Short id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "fare")
    private BigDecimal fare;

    @Column(name = "max_passengers")
    private Integer maxPassengers;

}