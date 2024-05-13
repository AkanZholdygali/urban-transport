package kz.akan.springcourse.urbantransport.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
public class StopOnRouteView {
    @EmbeddedId
    private StopOnRouteViewId id;

}