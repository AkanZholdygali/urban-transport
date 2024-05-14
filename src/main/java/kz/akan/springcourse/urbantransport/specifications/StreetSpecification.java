package kz.akan.springcourse.urbantransport.specifications;

import kz.akan.springcourse.urbantransport.models.Street;
import org.springframework.data.jpa.domain.Specification;

public class StreetSpecification {

    public static Specification<Street> hasNameContaining(String searchText) {
        return (root, query, builder) -> searchText == null ? builder.conjunction() : builder.like(root.get("name"), "%" + searchText + "%");
    }
}
