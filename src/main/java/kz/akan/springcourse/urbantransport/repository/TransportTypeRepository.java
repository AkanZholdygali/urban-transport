package kz.akan.springcourse.urbantransport.repository;

import kz.akan.springcourse.urbantransport.model.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeRepository extends JpaRepository<TransportType, Integer> {
}
