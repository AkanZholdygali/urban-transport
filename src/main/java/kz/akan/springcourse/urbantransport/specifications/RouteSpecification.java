package kz.akan.springcourse.urbantransport.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kz.akan.springcourse.urbantransport.models.Route;
import kz.akan.springcourse.urbantransport.models.TransportType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RouteSpecification {

    public static Specification<Route> hasRouteNoContaining(String searchText) {
        return (root, query, builder) -> searchText == null ? builder.conjunction() : builder.like(root.get("routeNo"), "%" + searchText + "%");
    }

    public static Specification<Route> hasTransportTypes(Boolean busChecked, Boolean trolleybusChecked) {
        return (root, query, builder) -> {
            if ((busChecked == null || !busChecked) && (trolleybusChecked == null || !trolleybusChecked)) {
                return builder.conjunction();
            }
            Join<Route, TransportType> typeJoin = root.join("type", JoinType.INNER);
            List<String> types = new ArrayList<>();
            if (busChecked != null && busChecked) types.add("Bus");
            if (trolleybusChecked != null && trolleybusChecked) types.add("Trolleybus");
            return typeJoin.get("title").in(types);
        };
    }

    public static Specification<Route> isWithinTimeRange(LocalTime time) {
        return (root, query, builder) -> time == null ? builder.conjunction() : builder.and(
                builder.lessThanOrEqualTo(root.get("firstTrip"), time),
                builder.greaterThanOrEqualTo(root.get("lastTrip"), time)
        );
    }
}