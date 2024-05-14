package kz.akan.springcourse.urbantransport.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Street {
    @Id
    @Column(name = "street_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

}