package kz.akan.springcourse.urbantransport.repositories;

import kz.akan.springcourse.urbantransport.models.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {
}
