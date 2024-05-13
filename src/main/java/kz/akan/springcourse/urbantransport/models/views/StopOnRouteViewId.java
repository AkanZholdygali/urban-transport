package kz.akan.springcourse.urbantransport.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StopOnRouteViewId implements java.io.Serializable {
    private static final long serialVersionUID = -1559602529625966510L;
    @Column(name = "route_no", nullable = false, length = 50)
    private String routeNo;

    @Column(name = "stop_id", nullable = false)
    private Integer stopId;

    @Column(name = "stop_order", columnDefinition = "tinyint not null")
    private Short stopOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StopOnRouteViewId entity = (StopOnRouteViewId) o;
        return Objects.equals(this.stopId, entity.stopId) &&
                Objects.equals(this.stopOrder, entity.stopOrder) &&
                Objects.equals(this.routeNo, entity.routeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, stopOrder, routeNo);
    }

}