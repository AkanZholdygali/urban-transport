package kz.akan.springcourse.urbantransport.repositories;

import kz.akan.springcourse.urbantransport.models.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportTypeRepository extends JpaRepository<TransportType, Integer> {
    Optional<TransportType> findByTitle(String title);
}
