package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Stop", indexes = {
        @Index(name = "IXFK_Stop_Street", columnList = "street_id")
})
public class Stop {
    @Id
    @Column(name = "stop_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "street_id")
    private Street street;

    @Column(name = "name", length = 50)
    private String name;

}