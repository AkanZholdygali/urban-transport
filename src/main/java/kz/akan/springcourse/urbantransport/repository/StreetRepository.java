package kz.akan.springcourse.urbantransport.repository;

import kz.akan.springcourse.urbantransport.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
}
