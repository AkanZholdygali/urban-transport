package kz.akan.springcourse.urbantransport.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kz.akan.springcourse.urbantransport.models.Transport;
import kz.akan.springcourse.urbantransport.models.TransportType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransportSpecification {

    public static Specification<Transport> hasLicensePlateNoContaining(String searchText) {
        return (root, query, builder) -> searchText == null ? builder.conjunction() : builder.like(root.get("licensePlateNo"), "%" + searchText + "%");
    }

    public static Specification<Transport> hasTransportTypes(Boolean busChecked, Boolean trolleybusChecked) {
        return (root, query, builder) -> {
            if ((busChecked == null || !busChecked) && (trolleybusChecked == null || !trolleybusChecked)) {
                return builder.conjunction();
            }
            Join<Transport, TransportType> typeJoin = root.join("type", JoinType.INNER);
            List<String> types = new ArrayList<>();
            if (busChecked != null && busChecked) types.add("Bus");
            if (trolleybusChecked != null && trolleybusChecked) types.add("Trolleybus");
            return typeJoin.get("title").in(types);
        };
    }

    public static Specification<Transport> hasManufactureYearBetween(Integer fromYear, Integer toYear) {
        return (root, query, builder) -> {
            if (fromYear == null && toYear == null) {
                return builder.conjunction();
            } else if (fromYear == null) {
                return builder.lessThanOrEqualTo(root.get("manufactureYear"), toYear);
            } else if (toYear == null) {
                return builder.greaterThanOrEqualTo(root.get("manufactureYear"), fromYear);
            } else {
                return builder.between(root.get("manufactureYear"), fromYear, toYear);
            }
        };
    }

    public static Specification<Transport> isFunctional(Boolean functional) {
        return (root, query, builder) -> functional == null ? builder.conjunction() : builder.equal(root.get("isFunctional"), functional);
    }
}

