package kz.akan.springcourse.urbantransport.specifications;

import kz.akan.springcourse.urbantransport.models.Stop;
import org.springframework.data.jpa.domain.Specification;

public class StopSpecification {

    public static Specification<Stop> hasNameContaining(String searchText) {
        return (root, query, builder) -> searchText == null ? builder.conjunction() : builder.like(root.get("name"), "%" + searchText + "%");
    }

    public static Specification<Stop> hasStreetNameContaining(String searchText) {
        return (root, query, builder) -> {
            if (searchText == null || searchText.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(root.join("street").get("name"), "%" + searchText + "%");
        };
    }
}
