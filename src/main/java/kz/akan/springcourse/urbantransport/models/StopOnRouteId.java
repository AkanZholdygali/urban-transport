package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StopOnRouteId implements java.io.Serializable {
    private static final long serialVersionUID = 5964473833625085649L;
    @Column(name = "route_no", nullable = false, length = 50)
    private String routeNo;

    @Column(name = "stop_order", columnDefinition = "tinyint not null")
    private Short stopOrder;

    @Column(name = "stop_id", nullable = false)
    private Integer stopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StopOnRouteId entity = (StopOnRouteId) o;
        return Objects.equals(this.stopId, entity.stopId) &&
                Objects.equals(this.stopOrder, entity.stopOrder) &&
                Objects.equals(this.routeNo, entity.routeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, stopOrder, routeNo);
    }

}