package kz.akan.springcourse.urbantransport.repository;

import kz.akan.springcourse.urbantransport.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {
}
